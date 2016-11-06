package com.furiapolitehnicii.logging;

import com.furiapolitehnicii.loggingserver.resources.ISeverity.Severity;;

public class LogRecord {
	private Severity severity;
	private String message;
	public LogRecord(Severity severity, String message) {
		this.severity = severity;
		this.message = message;
	}
	public Severity getSeverity() {
		return severity;
	}
	public String getMessage() {
		return message;
	}

}
