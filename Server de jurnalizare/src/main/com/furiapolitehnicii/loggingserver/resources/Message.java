package com.furiapolitehnicii.loggingserver.resources;

public class Message {
	private String content;
	private String clientID;
	private ISeverity.Severity severity;
	public Message(ISeverity.Severity severity, String clientID,
			String content) {
		this.content = content;
		this.clientID = clientID;
		this.severity = severity;
	}
	public ISeverity.Severity getSeverity() {
		return this.severity;
	}
	public String getClientID() {
		return this.clientID;
	}
	public String getContent() {
		return content;
	}
}
