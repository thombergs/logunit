package org.wickedsource.logunit.logback;

import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.LogUnitEvent;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class LogUnitLogbackConsoleAppender extends
		ConsoleAppender<ILoggingEvent> {

	private LogbackEventConverter converter = new LogbackEventConverter();

	@Override
	protected void append(ILoggingEvent event) {
		super.append(event);
		LogUnitEvent logunitEvent = converter.convert(event);
		LogUnit.get().consumeLogUnitEvent(logunitEvent);
	}

}
