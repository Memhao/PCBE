package com.furiapolitehnicii.behaviours;

import java.io.File;
/**
 *
 * @author Mihut
 *
 */
/*
 * @todo Change class name
 */
public class ClientThread extends Thread {
	private String clientName;
	private String clientID;
	private File file;
	private Job job;

	public ClientThread(String clientName, String clientID, File file,
			Job job) {
		this.clientName = clientName;
		this.clientID = clientID;
		this.file = file;
		this.job = job;
	}

	public String getClientName() {
		return clientName;
	}

	public File getFiles() {
		return file;
	}

	public String getClientID() {
		return clientID;
	}
	// TODO: implement the method
	@Override
	public void run() {
		job.execute();
	}

}
