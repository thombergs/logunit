package org.wickedsource.logunit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class allows defining and asserting {@link Expectation}s within a unit
 * test. The procedure should be as follows:
 * <ol>
 * <li>Make sure that you have the LogUnit modules for your logging framework
 * and your unit testing framework in your classpath (for example logunit-junit4
 * and logunit-log4j12 if you are using JUnit 4 as unit testing framework and
 * Log4J 1.2.x as your logging framework).</li>
 * <li>Configure your logging framework with the LogUnit appender so LogUnit is
 * notified of log events (LogUnitLog4JConsoleAppender in the case of log4j).</li>
 * <li>In your unit test, get an instance of the {@link LogUnit} class by
 * calling <code>LogUnit.get()</code>.</li>
 * <li>Use the <code>expect*</code> methods of the {@link LogUnit} instance to
 * define which log statements you expect to be logged in the unit test.</li>
 * <li>Call the code under test.</li>
 * <li>Call <code>LogUnit.assertExpectations()</code> to assert that all logging
 * expectations have been fulfilled.</li>
 * </ol>
 * 
 * @author Tom
 * 
 */
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

	/**
	 * This method uses the assertion facilities of a given unit test framework
	 * to assert that all expectations that have been defined are fulfilled.
	 * Thus, this method must be implemented differently for different unit test
	 * frameworks.
	 */
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
