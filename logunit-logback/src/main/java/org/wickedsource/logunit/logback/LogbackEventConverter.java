/**
 *   Copyright 2013 Tom Hombergs (tom.hombergs@gmail.com)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
 * @author Tom Hombergs <tom.hombergs@gmail.com>
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
