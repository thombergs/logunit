package org.wickedsource.logunit;

/**
 * Log event class used by LogUnit.
 * 
 * @author hombergs
 * 
 */
public class LogUnitEvent {

	private LogLevel level;

	private String message;

	private String logger;

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

}
