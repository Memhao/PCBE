package com.furiapolitehnicii.loggingserver.logging;

import com.furiapolitehnicii.loggingserver.resources.IResource;
import com.furiapolitehnicii.loggingserver.server.Configuration;
import com.furiapolitehnicii.loggingserver.server.Criteria;

public class LoggingStrategyFactory {

	public static ILoggingStrategy getStrategy(Criteria strategy,
			IResource resource, Configuration config) {
		if (strategy.equals(Criteria.CLIENT)) {
			return new ClientNameLoggingStrategy(resource, config);

		} else if (strategy.equals(Criteria.SEVERITY)) {
			return new MessageSeverityLoggingStrategy(resource, config);
		}
		return null;
	}
}
