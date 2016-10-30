package com.furiapolitehnicii.strategies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.Resource;

public class AuthorLoggingStrategy implements LoggingStrategy {
	private Resource resource;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String logPath;
	private int logSize;
	private int noOfRotations;
	private Map<String, FileHandler> clientsName = new HashMap<String, FileHandler>();

	public AuthorLoggingStrategy(Resource resource, String logPath, int logSize,
			int noOfRotations) {
		this.resource = resource;
		this.logPath = logPath;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
	}

	private FileHandler getFileHandler(String clientName) {
		if (!clientsName.containsKey(clientName)) {
			FileHandler fileHandler = null;
			try {
				fileHandler = new FileHandler(logPath + "/" + clientName,
						logSize, noOfRotations, true);
				clientsName.put(clientName, fileHandler);
				return fileHandler;
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

	private void logMessage(Message message) {

		ISeverity.Severity severity = message.getSeverity();
		switch (severity) {
			case INFO :
				logger.info(message.getContent());
				break;
			case WARNING :
				logger.warning(message.getContent());
				break;
			case ERROR :
			case CRITICAL :
				logger.severe(message.getContent());
				break;
			default :
				break;
		}
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				Message m = resource.take();
				String clientName = m.getAuthor();

				if (m.getSeverity() == ISeverity.Severity.EOF) {
					break;
				}
				FileHandler fileHandler = getFileHandler(clientName);
				fileHandler.setFormatter(new java.util.logging.Formatter() {
					@Override
					public String format(LogRecord logRecord) {
						return "[" + logRecord.getLevel() + "] " + "["
								+ clientName + "]" + logRecord.getMessage()
								+ "\r\n";
					}
				});
				logger.addHandler(fileHandler);
				logMessage(m);
				Handler[] handlers = logger.getHandlers();

				for (int i = 0; i < handlers.length; i++) {
					logger.removeHandler(handlers[i]);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
