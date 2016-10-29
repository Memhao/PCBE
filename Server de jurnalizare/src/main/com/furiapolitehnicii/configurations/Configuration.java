package com.furiapolitehnicii.configurations;

import com.furiapolitehnicii.behaviours.LoggingStrategy;

/**
 * TODO: look at Constants class on how to comment.
 *
 * @author Mihut
 *
 */
public class Configuration {
	private String name;
	private int maximNumberOfCliens;
	private int numberOfThreadsPerClient;
	private int logSize;
	private int noOfRotations;
	private LoggingStrategy loggingStrategy;
	private String filePath;

	public Configuration(String name, int maximNumberOfCliens,
			int numberOfThreadsPerClient, int logSize, int noOfRotations,
			LoggingStrategy loggingStrategy, String filePath) {
		super();
		this.name = name;
		this.maximNumberOfCliens = maximNumberOfCliens;
		this.numberOfThreadsPerClient = numberOfThreadsPerClient;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
		this.loggingStrategy = loggingStrategy;
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}

	public int getMaximNumberOfCliens() {
		return maximNumberOfCliens;
	}

	public int getNumberOfThreadsPerClient() {
		return numberOfThreadsPerClient;
	}

	public int getLogSize() {
		return logSize;
	}

	public int getNoOfRotations() {
		return noOfRotations;
	}

	public LoggingStrategy getLoggingStrategy() {
		return loggingStrategy;
	}

	public String getFilePath() {
		return filePath;
	}

}
