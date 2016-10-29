package com.furiapolitehnicii.behaviours;

public class ServerThread extends Thread {
	private Job job;

	public ServerThread(Job job) {
		// TODO Auto-generated constructor stub
		this.job = job;
	}

	public void run() {
		// TODO Auto-generated method stub
		job.execute();
	}
}
