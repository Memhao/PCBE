package com.furiapolitehnicii.loggingserver.server;

import com.furiapolitehnicii.loggingserver.client.Client;
import com.furiapolitehnicii.loggingserver.client.ClientThread;
import com.furiapolitehnicii.loggingserver.jobs.Job;
import com.furiapolitehnicii.loggingserver.jobs.LoggingJob;
import com.furiapolitehnicii.loggingserver.jobs.ReadingJob;
import com.furiapolitehnicii.loggingserver.logging.ILoggingStrategy;
import com.furiapolitehnicii.loggingserver.logging.LoggingStrategyFactory;
import com.furiapolitehnicii.loggingserver.resources.UniqResource;

public class Server {
	private String name;
	private Configuration config;
	private UniqResource resource;

	public Server(String name, Configuration config) {
		this.name = name;
		this.config = config;
		this.resource = UniqResource.getInstance();

	}
	public void startClient(Client client) {
		// TODO start client on action
		new Thread(new ClientThread(client.getAddress(),
				client.getLoggingFileName(), new ReadingJob(client.getPath(),
						client.getAddress(), resource))).start();

	}
	public void startServer() {
		ILoggingStrategy logstr = LoggingStrategyFactory
				.getStrategy(config.getCriteria(), resource, config);
		Job job = new LoggingJob(logstr);
		for (int i = 0; i < config.getnoOfLoggingThreads(); i++) {
			new Thread(new LoggingThread(job)).start();
		}
	}
	public void stopServer() {

	}
	public String getName() {
		return this.name;
	}
}
