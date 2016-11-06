package com.furiapolitehnicii.logging;

import java.io.File;

public class FileHandler {
	private File file;
	private int numberOfRotation;
	private int logSize;
	public FileHandler(File file, int numberOfRotation, int logSize) {
		this.file = file;
		this.numberOfRotation = numberOfRotation;
		this.logSize = logSize;
	}
	public File getFile() {
		return file;
	}
	public int getNumberOfRotation() {
		return numberOfRotation;
	}
	public int getLogSize() {
		return logSize;
	}
	public void publish(LogRecord logRecord) {

	}
	public void rotate() {

	}
}
