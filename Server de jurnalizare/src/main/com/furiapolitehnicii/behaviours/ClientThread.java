package com.furiapolitehnicii.behaviours;

import java.io.File;
import java.util.List;

import com.furiapolitehnicii.models.MessagesBucket;
/**
 * 
 * @author Mihut
 *
 */
public class ClientThread extends Thread {
	private String clientName;
	private List<File> files;

	public ClientThread(String name, List<File> files) {
		this.clientName = name;
		this.files = files;
	}

	public String getClientName() {
		return clientName;
	}

	public List<File> getFiles() {
		return files;
	}

	public MessagesBucket getMessagesBucket() {
		// TODO:Set a convetion about the lines written in files, save data in a
		// structure(Message object) --> use regular expression
		return null;
	}
	// TODO: implement the method
	@Override
	public void run() {

	}

}
