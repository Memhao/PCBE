package com.furiapolitehnicii.strategies;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.Resource;

public class IDLoggingStrategy implements LoggingStrategy {
	private volatile boolean run = true;
	private int pillsRecieved;
	private int noOfThreads;

	private Resource resource;
	private String logPath;
	private int logSize;
	private int noOfRotations;
	private ConcurrentHashMap<String, Logger> clientsName = new ConcurrentHashMap<String, Logger>();

	/**
	 *
	 * @param resource
	 * @param logPath
	 * @param logSize
	 * @param noOfRotations
	 * @param noOfThreads
	 *            that access the common resource
	 */
	public IDLoggingStrategy(Resource resource, String logPath, int logSize,
			int noOfRotations, int noOfThreads) {
		this.resource = resource;
		this.logPath = logPath;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
		this.noOfThreads = noOfThreads;
	}

	private Logger getLogger(String clientName) {
		if (!clientsName.containsKey(clientName)) {
			FileHandler fileHandler = null;
			try {
				fileHandler = new FileHandler(logPath + clientName, logSize,
						noOfRotations, true);
				fileHandler.setFormatter(new java.util.logging.Formatter() {
					@Override
					public String format(LogRecord logRecord) {
						return logRecord.getMessage() + "\r\n";
					}
				});
				Logger logger = Logger.getLogger(clientName);
				logger.addHandler(fileHandler);
				clientsName.put(clientName, logger);
				return logger;
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientsName.get(clientName);
	}

	private void logMessage(Logger logger, Message message) {

		ISeverity.Severity severity = message.getSeverity();
		switch (severity) {
			case INFO :
			case DEBUG :
				logger.info(message.toString());
				break;
			case WARNING :
				logger.warning(message.toString());
				break;
			case ERROR :
			case CRITICAL :
				logger.severe(message.toString());
				break;
			default :
				break;
		}
	}

	private boolean allGamesAreOver(ISeverity.Severity severity) {

		if (ISeverity.Severity.EOF.equals(severity)) {
			++pillsRecieved;
		}

		return pillsRecieved == noOfThreads ? true : false;
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		while (run) {
			try {
				Message m = resource.take();
				if (allGamesAreOver(m.getSeverity())) {
					run = false;
				} else {
					System.out.println(m + "[CONSUMER-" + m.getAuthor() + "_"
							+ this.logPath + "]");
					Logger logger = getLogger(m.getAuthor());
					logMessage(logger, m);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}
}
