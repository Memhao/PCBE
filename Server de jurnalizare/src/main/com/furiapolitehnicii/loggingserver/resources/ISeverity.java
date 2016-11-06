package com.furiapolitehnicii.loggingserver.resources;

public interface ISeverity {
	enum Severity {
		DEBUG, INFO, WARNING, ERROR, CRITICAL, NOSEVERITY, EOF
	}
	public static Severity getSeverity(String severity) {
		switch (severity) {
			case "DEBUG" :
				return Severity.DEBUG;
			case "INFO" :
				return Severity.INFO;
			case "WARNING" :
				return Severity.WARNING;
			case "ERROR" :
				return Severity.ERROR;
			case "CRITICAL" :
				return Severity.CRITICAL;
			default :
				return Severity.NOSEVERITY;
		}
	}
}
