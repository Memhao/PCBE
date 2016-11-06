package com.furiapolitehnicii.logging;

import com.furiapolitehnicii.loggingserver.resources.ISeverity.Severity;

public class Logger {
	private String loggerName;
	private FileHandler fileHandler;

	public Logger(String loggerName) {
		this.loggerName = loggerName;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	public FileHandler getFileHandler() {
		return fileHandler;
	}
	public String getLoggerName() {
		return loggerName;
	}

	public void logMessage(Severity severity, String message) {
		LogRecord logRecord = new LogRecord(severity, message);
		fileHandler.publish(logRecord);
	}
	public void info(String message) {
		Severity severity = Severity.INFO;
		logMessage(severity, message);
	}
	public void debug(String message) {
		Severity severity = Severity.DEBUG;
		logMessage(severity, message);
	}
	public void warning(String message) {
		Severity severity = Severity.WARNING;
		logMessage(severity, message);
	}
	public void critical(String message) {
		Severity severity = Severity.CRITICAL;
		logMessage(severity, message);
	}
	public void error(String message) {
		Severity severity = Severity.ERROR;
		logMessage(severity, message);
	}

}
