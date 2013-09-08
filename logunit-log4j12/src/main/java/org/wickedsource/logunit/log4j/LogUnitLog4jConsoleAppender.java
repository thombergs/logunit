package org.wickedsource.logunit.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.LogUnitEvent;

/**
 * A LogUnit-aware Log4J {@link ConsoleAppender}. Configure Log4J with this
 * appender to allow {@link LogUnit} to listen on log events and thus to enable
 * unit testing of log output.
 * 
 * @author hombergs
 * 
 */
public class LogUnitLog4jConsoleAppender extends ConsoleAppender {

	private Log4jEventConverter eventConverter = new Log4jEventConverter();

	@Override
	public synchronized void doAppend(LoggingEvent event) {
		super.doAppend(event);
		LogUnitEvent logunitEvent = eventConverter.convert(event);
		LogUnit.get().consumeLogUnitEvent(logunitEvent);
	}

}
