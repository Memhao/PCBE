package com.furiapolitehnicii.behaviours;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.furiapolitehnicii.constants.Constants;
import com.furiapolitehnicii.models.ISeverity;
import com.furiapolitehnicii.models.Message;
import com.furiapolitehnicii.models.SharedResource;

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
		Pattern pattern = Pattern.compile(SEVERITY_PATTERN);
		try {
			BufferedReader bf = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = bf.readLine()) != null) {
				Matcher m = pattern.matcher(line);
				String severity = m.group(1);
				String content = m.group(2);
				ISeverity.Severity sseverity = ISeverity.getSeverity(severity);

				sharedResource.put(new Message(sseverity, content, idClient));
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
