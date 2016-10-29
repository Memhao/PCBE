package com.furiapolitehnicii.models;
/**
 * TODO: look at Constants class on how to comment.
 *
 * @author Mihut
 *
 */
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
