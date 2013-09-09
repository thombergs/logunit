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
package org.wickedsource.logunit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A LogUnit expectation defines what is expected to be logged during a unit
 * test.
 * 
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 * 
 */
public class Expectation {

	private Pattern pattern;

	private LogLevel level;

	private String loggerName;

	private boolean fulfilled;

	private boolean inverted;

	/**
	 * Constructs an expectation with a given regular expression pattern. The
	 * expectation will be fulfilled if a log message matching this pattern is
	 * logged.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 */
	public Expectation(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * Constructs an expectation with a given regular expression pattern and a
	 * given log level. The expectation will be fulfilled if a log message
	 * matching the pattern with the given log level is logged.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 * @param level
	 *            the log level that the message is expected to be logged on.
	 */
	public Expectation(Pattern pattern, LogLevel level) {
		this.pattern = pattern;
		this.level = level;
	}

	/**
	 * Constructs an expectation with a given regular expression pattern, a
	 * given log level and a given logger name. The expectation will be
	 * fulfilled if a log message matching the given pattern is logged on the
	 * given log level and the logger with the given name.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 * @param level
	 *            the log level that the message is expected to be logged on.
	 * @param loggerName
	 *            the name of the logger that is expected to log the message.
	 */
	public Expectation(Pattern pattern, LogLevel level, String loggerName) {
		this.pattern = pattern;
		this.level = level;
		this.loggerName = loggerName;
	}

	/**
	 * Constructs an expectation with a given regular expression pattern. The
	 * expectation will be fulfilled if a log message matching this pattern is
	 * logged.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 */
	public Expectation(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	/**
	 * Constructs an expectation with a given regular expression pattern and a
	 * given log level. The expectation will be fulfilled if a log message
	 * matching the pattern with the given log level is logged.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 * @param level
	 *            the log level that the message is expected to be logged on.
	 */
	public Expectation(String pattern, LogLevel level) {
		this.pattern = Pattern.compile(pattern);
		this.level = level;
	}

	/**
	 * Constructs an expectation with a given regular expression pattern, a
	 * given log level and a given logger name. The expectation will be
	 * fulfilled if a log message matching the given pattern is logged on the
	 * given log level and the logger with the given name.
	 * 
	 * @param pattern
	 *            the pattern that is expected to be logged.
	 * @param level
	 *            the log level that the message is expected to be logged on.
	 * @param loggerName
	 *            the name of the logger that is expected to log the message.
	 */
	public Expectation(String pattern, LogLevel level, String loggerName) {
		this.pattern = Pattern.compile(pattern);
		this.level = level;
		this.loggerName = loggerName;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	/**
	 * Inverts this expectation so that it is fulfilled, if the given parameters
	 * have NOT been logged.
	 * 
	 * @return this object for chaining
	 */
	public Expectation invert() {
		this.inverted = true;
		return this;
	}

	/**
	 * Returns whether this expectation has been fulfilled or not (this takes
	 * into account whether this {@link Expectation} is inverted or not).
	 * 
	 * @return true if this expectation was fulfilled, false if not.
	 */
	public boolean isFulfilled() {
		if (inverted) {
			return !fulfilled;
		} else {
			return fulfilled;
		}
	}

	protected void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	/**
	 * Consumes a {@link LogUnitEvent} and compares the data in the event to the
	 * data expected by this {@link Expectation}. If the comparison is
	 * successful, this {@link Expectation}s "fulfilled" status is updated.s
	 * 
	 * @param event
	 *            the log event that contains the data to compare to the
	 *            expected data.
	 */
	public void consumeLogEvent(LogUnitEvent event) {
		if (!fulfilled) {
			this.fulfilled = matchesPattern(event) && matchesLogLevel(event)
					&& matchesLoggerName(event);
		}
	}

	private boolean matchesPattern(LogUnitEvent event) {
		Matcher matcher = pattern.matcher(event.getMessage());
		return matcher.matches();
	}

	private boolean matchesLogLevel(LogUnitEvent event) {
		if (this.level == null) {
			return true;
		} else {
			return this.level == event.getLevel();
		}
	}

	private boolean matchesLoggerName(LogUnitEvent event) {
		if (this.loggerName == null) {
			return true;
		} else {
			return this.loggerName.equals(event.getLogger());
		}
	}

	public String toString() {
		return String
				.format("[Expectation: %s [expected pattern: %s] [expected log level: %s] [expected logger name: %s]]",
						inverted ? "NOT" : "", this.pattern, this.level,
						this.loggerName);
	}
}
