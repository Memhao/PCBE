package com.furiapolitehnicii.loggingserver.client;

import com.furiapolitehnicii.loggingserver.jobs.Job;

public class ClientThread implements Runnable {
	private Client client;
	private Job job;

	public ClientThread(Client client, Job job) {
		this.client = client;
		this.job = job;
	}

	public Client getClient() {
		return client;
	}
	public void run() {
		job.execute();
	}

}
