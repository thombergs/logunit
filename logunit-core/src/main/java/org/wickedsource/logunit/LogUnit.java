package org.wickedsource.logunit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class LogUnit {

	private final Set<Expectation> expectations = new HashSet<Expectation>();

	private static LogUnit instance;

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

	/**
	 * Returns the singleton implementation of the {@link LogUnit} abstract
	 * super class.
	 * 
	 * @return the singleton implementation of the {@link LogUnit} abstract
	 *         super class.
	 * @throws IllegalStateException
	 *             if more than one implementations of {@link LogUnit} are found
	 *             on the classpath.
	 */
	public static LogUnit get() {
		if (instance == null) {
			synchronized (LogUnit.class) {
				ServiceLoader<LogUnit> serviceLoader = ServiceLoader
						.load(LogUnit.class);
				Iterator<LogUnit> implementations = serviceLoader.iterator();
				LogUnit implementation = null;
				boolean implementationFound = false;
				while (implementations.hasNext()) {
					LogUnit currentImplementation = implementations.next();
					implementation = currentImplementation;
					if (implementationFound) {
						throw new IllegalStateException(
								String.format(
										"There are at least two implementations of %s registered (%s and %s). Please remove all implementations but one.",
										LogUnit.class.getName(),
										currentImplementation.getClass()
												.getName(), implementation
												.getClass().getName()));
					}
					implementationFound = true;
				}
				instance = implementation;
			}
		}
		return instance;
	}

}
