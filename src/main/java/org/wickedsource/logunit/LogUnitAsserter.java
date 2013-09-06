package org.wickedsource.logunit;

public interface LogUnitAsserter {

	void assertLogged(String text);

	void assertLogged(String text, LogLevel level);

	void assertLogged(String text, LogLevel level, String logger);

	void assertNotLogged(String text);

	void assertNotLogged(String text, LogLevel level);

	void assertNotLogged(String text, LogLevel level, String logger);
	
}
