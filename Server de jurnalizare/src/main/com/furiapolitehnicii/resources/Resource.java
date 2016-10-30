package com.furiapolitehnicii.resources;

public interface Resource {
	void put(Message smt) throws InterruptedException;
	Message take() throws InterruptedException;

}
