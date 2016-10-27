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
	private boolean orderMessagesBySeverity;
	private boolean orderMessagesByClientName;
	private int numberOfThreadsPerClient;

	public Configuration(String name, int maximNumberOfCliens,
			boolean orderMessagesBySeverity, boolean orderMessagesByClientName,
			int numberOfThreadsPerClient) {
		this.name = name;
		this.maximNumberOfCliens = maximNumberOfCliens;
		this.orderMessagesBySeverity = orderMessagesBySeverity;
		this.orderMessagesByClientName = orderMessagesByClientName;
		this.numberOfThreadsPerClient = numberOfThreadsPerClient;

		if (orderMessagesByClientName == false
				&& orderMessagesBySeverity == false) {
			orderMessagesByClientName = true;
			System.out.println(
					"Have to choose a strategy to order the messages. Strategy was set to \"orderMessagesByClientName\".");
		}
	}

	public String getName() {
		return name;
	}

	public int getMaximNumberOfCliens() {
		return maximNumberOfCliens;
	}

	public boolean isOrderMessagesBySeverity() {
		return orderMessagesBySeverity;
	}

	public boolean isOrderMessagesByClientName() {
		return orderMessagesByClientName;
	}

	public int getNumberOfThreadsPerClient() {
		return numberOfThreadsPerClient;
	}

}
