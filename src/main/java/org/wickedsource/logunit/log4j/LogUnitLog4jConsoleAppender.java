package org.wickedsource.logunit.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.wickedsource.logunit.InMemoryLog;
import org.wickedsource.logunit.LogUnitEvent;

/**
 * A LogUnit-aware Log4J {@link ConsoleAppender}. Additionally to the console,
 * the output will be stored in memory to later be able to do asserts on the log
 * events in memory.
 * 
 * @author hombergs
 * 
 */
public class LogUnitLog4jConsoleAppender extends ConsoleAppender {

	private Log4jEventConverter eventConverter = new Log4jEventConverter();

	@Override
	public synchronized void doAppend(LoggingEvent event) {
		super.doAppend(event);
		LogUnitEvent logUnitEvent = eventConverter.convert(event);
		InMemoryLog.getThreadLocal().append(logUnitEvent);
	}

}
