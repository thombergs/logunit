package org.wickedsource.logunit;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class AbstractLogUnitAsserter {

	private final Set<Expectation> expectations = new HashSet<Expectation>();

	public void expect(String text) {
		expectations.add(new Expectation(text));
	}

	public void expect(String text, LogLevel level) {
		expectations.add(new Expectation(text, level));
	}

	public void expect(String text, LogLevel level, String loggerName) {
		expectations.add(new Expectation(text, level, loggerName));
	}

	public void expect(Pattern pattern) {
		expectations.add(new Expectation(pattern));
	}

	public void expect(Pattern pattern, LogLevel level) {
		expectations.add(new Expectation(pattern, level));
	}

	public void expect(Pattern pattern, LogLevel level, String loggerName) {
		expectations.add(new Expectation(pattern, level, loggerName));
	}

	public void expect(Expectation expectation) {
		expectations.add(expectation);
	}

	public void expectNot(String text) {
		expectations.add(new Expectation(text).invert());
	}

	public void expectNot(String text, LogLevel level) {
		expectations.add(new Expectation(text, level).invert());
	}

	public void expectNot(String text, LogLevel level, String loggerName) {
		expectations.add(new Expectation(text, level, loggerName).invert());
	}

	public void expectNot(Pattern pattern) {
		expectations.add(new Expectation(pattern).invert());
	}

	public void expectNot(Pattern pattern, LogLevel level) {
		expectations.add(new Expectation(pattern, level).invert());
	}

	public void expectNot(Pattern pattern, LogLevel level, String loggerName) {
		expectations.add(new Expectation(pattern, level, loggerName).invert());
	}

	public void expectNot(Expectation expectation) {
		expectations.add(expectation.invert());
	}

	public void consumeLogUnitEvent(LogUnitEvent event) {
		for (Expectation expectation : expectations) {
			expectation.consumeLogEvent(event);
		}
	}

	public abstract void assertExpectations();

	public Set<Expectation> getExpectations() {
		return expectations;
	}

}
