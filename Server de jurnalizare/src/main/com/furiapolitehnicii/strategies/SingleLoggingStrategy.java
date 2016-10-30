package com.furiapolitehnicii.strategies;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.Resource;

public class SingleLoggingStrategy implements LoggingStrategy {

	private Resource resource;
	private FileHandler fileHandler;
	private String clientFileName;
	private String clientID;
	private Logger logger = Logger.getLogger("MyLog");
	public SingleLoggingStrategy(Resource resource, String path,
			String clientFileName, String clientID, int logSize,
			int noOfRotations) {
		this.resource = resource;
		this.clientFileName = clientFileName;
		this.clientID = clientID;
		// TODO Auto-generated constructor stub
		String filePath = path + this.clientFileName + "_" + this.clientID + "_"
				+ ".txt";
		try {
			this.fileHandler = new FileHandler(filePath, logSize, noOfRotations,
					true);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileHandler.setFormatter(new java.util.logging.Formatter() {
			@Override
			public String format(LogRecord logRecord) {
				return logRecord.getMessage() + "\r\n";
			}
		});
		logger.addHandler(fileHandler);
	}
	@Override
	public void log() {
		// TODO Auto-generated method stub
		int i = 0;
		while (true) {
			i++;
			try {
				Message m = resource.take();
				if (m.getSeverity() == ISeverity.Severity.EOF) {
					System.out.println("########" + i + "######");
					break;
				}

				System.out.println(m + "[CONSUMER-" + this.clientID + "_"
						+ this.clientFileName + "]");
				logger.info(m.toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
	}

}
