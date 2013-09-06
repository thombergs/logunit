package org.wickedsource.logunit.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.wickedsource.logunit.LogLevel;
import org.wickedsource.logunit.LogUnitEvent;

public class Log4jEventConverter {

	/**
	 * Converts a log4j {@link LoggingEvent} to a {@link LogUnitEvent}.
	 */
	public LogUnitEvent convert(LoggingEvent event) {
		LogUnitEvent logUnitEvent = new LogUnitEvent();
		logUnitEvent.setLevel(mapLevel(event));
		logUnitEvent.setLogger(event.getLoggerName());
		logUnitEvent.setMessage(event.getMessage().toString());
		return logUnitEvent;
	}

	private LogLevel mapLevel(LoggingEvent event) {
		Level level = event.getLevel();
		if (level == Level.DEBUG) {
			return LogLevel.DEBUG;
		} else if (level == Level.ERROR) {
			return LogLevel.ERROR;
		} else if (level == Level.FATAL) {
			return LogLevel.FATAL;
		} else if (level == Level.INFO) {
			return LogLevel.INFO;
		} else if (level == Level.TRACE) {
			return LogLevel.TRACE;
		} else if (level == Level.WARN) {
			return LogLevel.WARN;
		}
		throw new IllegalArgumentException(String.format("LogUnit does not support the log4j log level %s", level));
	}

}
