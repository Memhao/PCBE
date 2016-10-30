package com.furiapolitehnicii.main;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.furiapolitehnicii.jobs.LoggingJob;
import com.furiapolitehnicii.jobs.ReadingJob;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.SharedResource;
import com.furiapolitehnicii.strategies.IDLoggingStrategy;
import com.furiapolitehnicii.threads.client.ClientThread;
import com.furiapolitehnicii.threads.server.ServerThread;

public class Main {
	public static void main(String[] args) {
		// TODO SETUP CLASS LOADER LOGGING LOGGER
		BlockingQueue<Message> blockz = new LinkedBlockingQueue<Message>();
		SharedResource res = new SharedResource(blockz);
		File file = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\file.txt");

		File flie = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\flie.txt");

		File fiel = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\fiel.txt");
		ReadingJob job0 = new ReadingJob(file, "xxx", res);
		ReadingJob job1 = new ReadingJob(flie, "yyy", res);
		ReadingJob job2 = new ReadingJob(fiel, "zzz", res);
		LoggingJob job_o = new LoggingJob(
				new IDLoggingStrategy(res, "src\\", 1000, 3, 3));

		ClientThread c1 = new ClientThread("a", "xxx", file, job0);
		ClientThread c2 = new ClientThread("b", "yyy", flie, job1);
		ClientThread c3 = new ClientThread("c", "zzz", fiel, job2);

		ServerThread s1 = new ServerThread(job_o);
		ServerThread s2 = new ServerThread(job_o);
		ServerThread s3 = new ServerThread(job_o);

		c1.start();
		c2.start();
		c3.start();

		s1.start();
		s2.start();
		s3.start();

	}

}
