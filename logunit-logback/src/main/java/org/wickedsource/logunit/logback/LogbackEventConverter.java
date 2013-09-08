package org.wickedsource.logunit.logback;

import org.wickedsource.logunit.LogLevel;
import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.LogUnitEvent;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * A LogUnit-aware Logback {@link ConsoleAppender}. Configure logback with this
 * appender to allow {@link LogUnit} to listen on log events and thus to enable
 * unit testing of log output.
 * 
 * @author hombergs
 * 
 */
public class LogbackEventConverter {

	public LogUnitEvent convert(ILoggingEvent event) {
		LogUnitEvent logunitEvent = new LogUnitEvent();
		logunitEvent.setMessage(event.getFormattedMessage());
		logunitEvent.setLevel(mapLevel(event));
		logunitEvent.setLogger(event.getLoggerName());
		return logunitEvent;
	}

	private LogLevel mapLevel(ILoggingEvent event) {
		Level level = event.getLevel();
		if (level == Level.DEBUG) {
			return LogLevel.DEBUG;
		} else if (level == Level.ERROR) {
			return LogLevel.ERROR;
		} else if (level == Level.INFO) {
			return LogLevel.INFO;
		} else if (level == Level.TRACE) {
			return LogLevel.TRACE;
		} else if (level == Level.WARN) {
			return LogLevel.WARN;
		}
		throw new IllegalArgumentException(String.format(
				"LogUnit does not support the log4j log level %s", level));
	}

}
