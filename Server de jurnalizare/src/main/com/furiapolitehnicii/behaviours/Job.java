package com.furiapolitehnicii.behaviours;

public interface Job {
	String SEVERITY_PATTERN = "[\\s]*[\\[][\\s]*(INFO|DEBUG|WARNING|ERROR)[\\s]*[\\]][\\s]*(.*)";
	void execute();
}
