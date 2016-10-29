package com.furiapolitehnicii.behaviours;

import java.io.File;
import java.util.List;

import com.furiapolitehnicii.models.SharedResource;
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
	private List<File> files;
	private Job job;

	public ClientThread(String clientName, String clientID, List<File> files,
			Job job) {
		this.clientName = clientName;
		this.clientID = clientID;
		this.files = files;
		this.job = job;
	}

	public String getClientName() {
		return clientName;
	}

	public List<File> getFiles() {
		return files;
	}

	public String getClientID() {
		return clientID;
	}

	public SharedResource getMessagesBucket() {
		// TODO:Set a convetion about the lines written in files, save data in a
		// structure(Message object) --> use regular expression
		return null;
	}
	// TODO: implement the method
	@Override
	public void run() {

	}

}
