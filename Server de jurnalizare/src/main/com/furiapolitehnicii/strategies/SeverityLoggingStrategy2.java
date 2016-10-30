package com.furiapolitehnicii.strategies;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.furiapolitehnicii.constants.ISeverity;
import com.furiapolitehnicii.resources.Message;
import com.furiapolitehnicii.resources.Resource;

public class SeverityLoggingStrategy2 implements LoggingStrategy {
	private Resource resource;
	private String logPath;
	private int logSize;
	private int noOfRotations;
	private Map<ISeverity.Severity, FileWriter> severityFileMap = new HashMap<ISeverity.Severity, FileWriter>();
	private int counterPills = 0;
	private Set<String> clientsName = new HashSet<String>();

	public SeverityLoggingStrategy2(Resource resource, String logPath,
			int logSize, int noOfRotations) {
		this.resource = resource;
		this.logPath = logPath;
		this.logSize = logSize;
		this.noOfRotations = noOfRotations;
	}

	private FileWriter getFileWriter(ISeverity.Severity severity)
			throws IOException {
		if (!severityFileMap.containsKey(severity)
				&& severity != ISeverity.Severity.EOF) {
			File file = new File(logPath + "/" + severity.toString());
			FileWriter fw = new FileWriter(file, true);
			severityFileMap.put(severity, fw);
			return fw;
		}
		return severityFileMap.get(severity);
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
	public void log() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				Message m = resource.take();
				System.out.println(Thread.currentThread() + " " + m.toString());
				String clientName = m.getAuthor();
				ISeverity.Severity severity = m.getSeverity();
				if (severity != ISeverity.Severity.EOF)
					synchronized (clientsName) {
						clientsName.add(clientName);
					}
				else
					synchronized (this) {
						counterPills++;
					}
				if (counterPills == clientsName.size() && counterPills != 0) {
					synchronized (severityFileMap) {
						for (Map.Entry<ISeverity.Severity, FileWriter> entry : severityFileMap
								.entrySet()) {
							entry.getValue().close();
						}
					}
					Thread.currentThread().interrupt();
					break;
				}

				FileWriter file = getFileWriter(severity);
				try {
					logMessage(file, m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}

		}
	}
}
