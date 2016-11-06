package com.furiapolitehnicii.loggingserver.client;

public class Client {
	private String folderPath;

	public Client(String path) {
		this.folderPath = path;
	}
	public String getPath() {
		return this.folderPath;
	}
	public String getClientName() {
		if (folderPath.contains("\\"))
			return folderPath.substring(folderPath.lastIndexOf('\\') + 1);
		return folderPath.substring(folderPath.lastIndexOf('/') + 1);
	}
}
