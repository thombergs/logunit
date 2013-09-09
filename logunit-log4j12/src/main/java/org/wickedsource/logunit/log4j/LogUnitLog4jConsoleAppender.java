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
