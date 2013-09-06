package org.wickedsource.logunit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expectation {

	private Pattern pattern;

	private LogLevel level;

	private String loggerName;

	private boolean fulfilled;

	private boolean inverted;

	public Expectation(Pattern pattern) {
		this.pattern = pattern;
	}

	public Expectation(Pattern pattern, LogLevel level) {
		this.pattern = pattern;
		this.level = level;
	}

	public Expectation(Pattern pattern, LogLevel level, String loggerName) {
		this.pattern = pattern;
		this.level = level;
		this.loggerName = loggerName;
	}

	public Expectation(String text) {
		this.pattern = Pattern.compile(text);
	}

	public Expectation(String text, LogLevel level) {
		this.pattern = Pattern.compile(text);
		this.level = level;
	}

	public Expectation(String text, LogLevel level, String loggerName) {
		this.pattern = Pattern.compile(text);
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
	 * Returns whether this expectation has been fulfilled or not.
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
				.format("[Expectation: [expected pattern: %s] [expected log level: %s] [expected logger name: %s]]",
						this.pattern, this.level, this.loggerName);
	}
}
