package com.furiapolitehnicii.models;

import java.util.ArrayList;
import java.util.List;
/**
 * TODO: look at Constants class on how to comment.
 * 
 * @author Mihut
 *
 */
public class MessagesBucket {
	private List<Message> messages;

	public MessagesBucket() {
		messages = new ArrayList<Message>();
	}

	public MessagesBucket(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Message getMessage(int index) {
		return messages.get(index);
	}

}
