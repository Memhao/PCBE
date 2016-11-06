package com.furiapolitehnicii.loggingserver.server;

import java.io.File;

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
	public void startClients(String inputPath) {
		File inputFolder = new File(inputPath);
		if (inputFolder != null && inputFolder.isDirectory()) {
			File[] folders = inputFolder.listFiles();
			for (File folder : folders) {
				if (folder != null && folder.isDirectory()) {
					Client client = new Client(folder.getAbsolutePath());
					ReadingJob readingJob = new ReadingJob(
							folder.getAbsolutePath(), client.getClientName(),
							resource);
					ClientThread clientThread = new ClientThread(client,
							readingJob);
					Thread thread = new Thread(clientThread);
					thread.start();
				} else
					System.err.println(folder.getName()
							+ " is not a folder. The file was ignored.");
			}
		} else
			System.err.println("Log path from GUI does not represent a folder");

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
