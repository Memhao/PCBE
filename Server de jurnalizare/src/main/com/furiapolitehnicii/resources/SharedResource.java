package com.furiapolitehnicii.resources;

import java.util.concurrent.BlockingQueue;
/**
 * TODO: look at Constants class on how to comment.
 *
 * @author Mihut
 *
 */
public class SharedResource implements Resource {
	private final BlockingQueue<Message> messages;
	public SharedResource(BlockingQueue<Message> messages) {
		// TODO Auto-generated constructor stub
		this.messages = messages;
	}
	@Override
	public void put(Message smt) throws InterruptedException {
		// TODO Auto-generated method stub
		messages.put(smt);
	}

	@Override
	public Message take() throws InterruptedException {
		// TODO Auto-generated method stub
		return messages.take();
	}

}
