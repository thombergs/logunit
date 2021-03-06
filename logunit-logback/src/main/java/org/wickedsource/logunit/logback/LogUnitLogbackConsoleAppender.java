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

import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.LogUnitEvent;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

/**
 * A LogUnit-aware Logback {@link ConsoleAppender}. Configure Logback with this
 * appender to allow {@link LogUnit} to listen on log events and thus to enable
 * unit testing of log output.
 * 
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 * 
 */
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
