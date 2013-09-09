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
package org.wickedsource.logunit.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.wickedsource.logunit.LogLevel;
import org.wickedsource.logunit.LogUnitEvent;

/**
 * Converts log4j {@link LoggingEvent}s to a {@link LogUnitEvent}.
 * 
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class Log4jEventConverter {

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
		throw new IllegalArgumentException(String.format(
				"LogUnit does not support the log4j log level %s", level));
	}

}
