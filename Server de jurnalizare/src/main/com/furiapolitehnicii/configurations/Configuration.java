package com.furiapolitehnicii.configurations;
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

	public Configuration(String name, int maximNumberOfCliens,
			int numberOfThreadsPerClient, int logSize, int noOfRotations,
			LoggingStrategy loggingStrategy) {
		this.name = name;
		this.maximNumberOfCliens = maximNumberOfCliens;
		this.numberOfThreadsPerClient = numberOfThreadsPerClient;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
		this.loggingStrategy = loggingStrategy;
	}
	public LoggingStrategy getLoggingStrategy() {
		return loggingStrategy;
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

}
