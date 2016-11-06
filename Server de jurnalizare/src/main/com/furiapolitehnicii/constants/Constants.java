package com.furiapolitehnicii.constants;

import com.furiapolitehnicii.loggingserver.resources.ISeverity;
import com.furiapolitehnicii.loggingserver.resources.Message;

public interface Constants {

	Message POISON_PILL = new Message(ISeverity.Severity.EOF, "", "");
	String SEVERITY_PATTERN = "[\\s]*[\\[][\\s]*(INFO|DEBUG|WARNING|ERROR|CRITICAL)[\\s]*[\\]][\\s]*(.*)";

}
