package com.furiapolitehnicii.constants;

import com.furiapolitehnicii.models.ISeverity;
import com.furiapolitehnicii.models.Message;

/**
 * TODO: comment all the classes and all the methods, except for the getters and
 * setters. Authors have to be like below:
 *
 * @author Mihut Andrei
 * @author Memetea Cosmin
 * @author Balaci Daiana
 * @author Panfilii Ion
 *
 */
public interface Constants {
	Message POISON_PILL = new Message(ISeverity.Severity.EOF, "", "");
}
