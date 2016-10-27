package com.furiapolitehnicii.behaviours;

import com.furiapolitehnicii.models.MessagesBucket;

/**
 * TODO: look at Constants class on how to comment. Change the class name
 * 
 * @author Mihut
 *
 */
public class LogClientThread extends Thread {
	private String logClientName;
	private MessagesBucket messages;
	// implement addMessage method
	public LogClientThread(String logClientName, MessagesBucket messages) {
		this.logClientName = logClientName;
		this.messages = messages;
	}
	public String getLogClientName() {
		return logClientName;
	}

	public MessagesBucket getMessages() {
		return messages;
	}

	@Override
	public void run() {

	}

}
