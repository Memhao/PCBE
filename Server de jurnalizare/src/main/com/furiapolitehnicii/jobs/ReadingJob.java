package com.furiapolitehnicii.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.furiapolitehnicii.constants.Constants;
import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.SharedResource;

public class ReadingJob implements Job {
	private File file;
	private String idClient;
	private SharedResource sharedResource;

	public ReadingJob(File file, String idClient,
			SharedResource sharedResource) {
		this.file = file;
		this.idClient = idClient;
		this.sharedResource = sharedResource;
	}
	// TODO Test Case need for syncronization
	private void getMessages() throws InterruptedException {
		Pattern pattern = Pattern.compile(Constants.SEVERITY_PATTERN);
		try {
			BufferedReader bf = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = bf.readLine()) != null) {
				Matcher m = pattern.matcher(line);
				String severity = null;
				String content = null;
				while (m.find()) {
					severity = m.group(1);
					content = m.group(2);
				}
				// TODO review this
				if (severity != null && content != null) {
					ISeverity.Severity sseverity = ISeverity
							.getSeverity(severity);
					Message msg = new Message(sseverity, content, idClient);
					System.out.println(msg);
					sharedResource.put(msg);

				}

			}
			bf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sharedResource.put(Constants.POISON_PILL);

	}
	// TODO This thread should not be interrupted
	@Override
	public void execute() {
		try {
			getMessages();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
