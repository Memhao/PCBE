package com.furiapolitehnicii.strategies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.Resource;

public class NameLoggingStrategy implements LoggingStrategy {
	private Resource resource;
	private String logPath;
	private int logSize;
	private int noOfRotations;
	private Map<String, FileWriter> clientFileMap = new HashMap<String, FileWriter>();

	public NameLoggingStrategy(Resource resource, String logPath, int logSize,
			int noOfRotations) {
		this.resource = resource;
		this.logPath = logPath;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
	}
	private FileWriter getFileWriter(String clientName) throws IOException {
		if (!clientFileMap.containsKey(clientName)) {
			File file = new File(logPath + "/" + clientName.toString());
			FileWriter fw = new FileWriter(file, true);
			clientFileMap.put(clientName, fw);
			return fw;
		}
		return clientFileMap.get(clientName);
	}
	private String prettyFormat(ISeverity.Severity severity,
			String clientName) {
		return "[" + severity.toString() + "]" + "[" + clientName + "] ";
	}
	private void logMessage(FileWriter fw, Message message) throws IOException {

		ISeverity.Severity severity = message.getSeverity();
		switch (severity) {
			case INFO :
			case DEBUG :
				fw.append(prettyFormat(message.getSeverity(),
						message.getAuthor()));
				fw.append(message.getContent() + System.lineSeparator());
				break;
			case WARNING :
				fw.append(prettyFormat(message.getSeverity(),
						message.getAuthor()));
				fw.append(message.getContent() + System.lineSeparator());
				break;
			case ERROR :
			case CRITICAL :
				fw.append(prettyFormat(message.getSeverity(),
						message.getAuthor()));
				fw.append(message.getContent() + System.lineSeparator());
				break;
			default :
				break;
		}
	}

	@Override
	public synchronized void log() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				Message m = resource.take();
				String clientName = m.getAuthor();

				if (m.getSeverity() == ISeverity.Severity.EOF) {
					for (Map.Entry<String, FileWriter> entry : clientFileMap
							.entrySet()) {
						entry.getValue().close();
					}
					break;
				}

				FileWriter file = getFileWriter(clientName);
				try {
					logMessage(file, m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(m + "[" + m.getAuthor() + "]");

			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}

		}
	}
}
