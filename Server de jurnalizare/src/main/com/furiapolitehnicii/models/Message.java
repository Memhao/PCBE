package com.furiapolitehnicii.models;
/**
 * TODO: look at Constants class on how to comment.
 *
 * @author Mihut
 *
 */
public class Message {
	private ISeverity.Severity severity;
	private String content;
	private String author;

	public Message(ISeverity.Severity severity, String content, String author) {
		this.severity = severity;
		this.content = content;
		this.author = author;
	}

	public ISeverity.Severity getSeverity() {
		return severity;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

}
