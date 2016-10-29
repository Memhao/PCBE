package com.furiapolitehnicii.main;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.furiapolitehnicii.behaviours.AuthorLoggingStrategy;
import com.furiapolitehnicii.behaviours.ClientThread;
import com.furiapolitehnicii.behaviours.LoggingJob;
import com.furiapolitehnicii.behaviours.ReadingJob;
import com.furiapolitehnicii.behaviours.ServerThread;
import com.furiapolitehnicii.models.Message;
import com.furiapolitehnicii.models.SharedResource;

public class Main {
	public static void main(String[] args) {
		// TODO SETUP CLASS LOADER LOGGING LOGGER
		BlockingQueue<Message> blockz = new LinkedBlockingQueue<Message>();
		SharedResource res = new SharedResource(blockz);
		File file = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\file.txt");

		File flie = new File(
				"C:\\Users\\Xander\\git\\PCBE\\Server de jurnalizare\\src\\resources\\flie.txt");
		ReadingJob job0 = new ReadingJob(file, "xxx", res);
		ReadingJob job1 = new ReadingJob(flie, "yyy", res);

		ClientThread c1 = new ClientThread("a", "xxx", file, job0);
		ClientThread c2 = new ClientThread("b", "yyy", flie, job1);
		c1.start();
		c2.start();
		LoggingJob job2 = new LoggingJob(
				new AuthorLoggingStrategy(res, "src\\", 1000, 2));
		ServerThread c3 = new ServerThread(job2);
		c3.start();

	}

}
