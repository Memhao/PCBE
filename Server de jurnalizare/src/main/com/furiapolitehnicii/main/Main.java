package com.furiapolitehnicii.main;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.furiapolitehnicii.jobs.LoggingJob;
import com.furiapolitehnicii.jobs.ReadingJob;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.SharedResource;
import com.furiapolitehnicii.strategies.SeverityLoggingStrategy;
import com.furiapolitehnicii.threads.client.ClientThread;
import com.furiapolitehnicii.threads.server.ServerThread;

public class Main {
	public static void main(String[] args) {
		// TODO SETUP CLASS LOADER LOGGING LOGGER
		BlockingQueue<Message> blockz = new LinkedBlockingQueue<Message>();
		SharedResource res = new SharedResource(blockz);
		BlockingQueue<Message> blocky = new LinkedBlockingQueue<Message>();
		SharedResource rez = new SharedResource(blocky);
		File file = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\file.txt");

		File flie = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\flie.txt");
		ReadingJob job0 = new ReadingJob(file, "xxx", res);
		ReadingJob job1 = new ReadingJob(flie, "yyy", res);
		// LoggingJob job2 = new LoggingJob(
		// new ClientIDLoggingStrategy(res, "src\\", 500, 2));
		// LoggingJob job3 = new LoggingJob(
		// new ClientIDLoggingStrategy(res, "src\\", 500, 3));
		//
		// LoggingJob job2 = new LoggingJob(
		// new AuthorLoggingStrategy(res, "src\\", 1000, 2));
		// LoggingJob job3 = new LoggingJob(
		// new AuthorLoggingStrategy(res, "src\\", 1000, 3));

		LoggingJob job2 = new LoggingJob(
				new SeverityLoggingStrategy(res, "src\\", 500, 2));
		LoggingJob job3 = new LoggingJob(
				new SeverityLoggingStrategy(res, "src\\", 500, 3));
		ClientThread c1 = new ClientThread("a", "xxx", file, job0);
		ClientThread c2 = new ClientThread("b", "yyy", flie, job1);

		ServerThread c3 = new ServerThread(job2);
		ServerThread c4 = new ServerThread(job3);
		c1.start();
		c2.start();

		c3.start();
		c4.start();

	}

}
