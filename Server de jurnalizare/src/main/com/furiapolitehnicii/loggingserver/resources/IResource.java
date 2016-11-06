package com.furiapolitehnicii.loggingserver.resources;

public interface IResource {
	void put(Message smt) throws InterruptedException;
	Message take() throws InterruptedException;
}
