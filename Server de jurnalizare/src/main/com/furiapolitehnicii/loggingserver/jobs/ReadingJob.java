package com.furiapolitehnicii.loggingserver.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.furiapolitehnicii.constants.Constants;
import com.furiapolitehnicii.loggingserver.resources.IResource;
import com.furiapolitehnicii.loggingserver.resources.ISeverity;
import com.furiapolitehnicii.loggingserver.resources.Message;

public class ReadingJob implements Job {
	private final static Message poisonPill = new Message(
			ISeverity.Severity.EOF, "", "");
	private final IResource messages;
	private String folderPath;
	private String clientID;

	private void getFromFolder() throws InterruptedException {

		try {
			Pattern pattern = Pattern.compile(Constants.SEVERITY_PATTERN);
			File folder = new File(folderPath);
			if (folder != null && folder.isDirectory()) {
				File[] files = folder.listFiles();
				for (File file : files) {
					BufferedReader br = new BufferedReader(
							new FileReader(file.getAbsolutePath()));
					String line;
					while ((line = br.readLine()) != null) {

						Matcher m = pattern.matcher(line);
						String severity = null;
						String content = null;
						while (m.find()) {
							severity = m.group(1);
							content = m.group(2);
						}

						if (severity != null && content != null) {
							ISeverity.Severity sseverity = ISeverity
									.getSeverity(severity);
							Message msg = new Message(sseverity, clientID,
									content);
							messages.put(msg);
						}
						System.out.println(line);
					}
					br.close();
				}
			} else
				System.err.println(clientID + ": log path not a folder!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		messages.put(poisonPill);
		System.out.println("__________________________");
	}

	public ReadingJob(String folderPath, String clientID, IResource messages) {
		this.folderPath = folderPath;
		this.messages = messages;
		this.clientID = clientID;
	}
	public void execute() {
		try {
			getFromFolder();
		} catch (InterruptedException e) {
			e.printStackTrace(); // --reading was finished
			Thread.currentThread().interrupt(); // --preserve the request for
												// interruption
			return;// -- here comes the termination
		}

	}

}
