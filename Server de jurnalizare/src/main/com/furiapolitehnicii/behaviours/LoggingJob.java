package com.furiapolitehnicii.behaviours;

public class LoggingJob implements Job {

	private LoggingStrategy loggingStrategy;

	public LoggingJob(LoggingStrategy loggingStrategy) {
		this.loggingStrategy = loggingStrategy;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		loggingStrategy.log();
	}

}
