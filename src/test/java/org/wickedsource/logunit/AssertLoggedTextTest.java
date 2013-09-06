package org.wickedsource.logunit;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.wickedsource.logunit.log4j.Log4jAsserter;

public class AssertLoggedTextTest {

	Logger logger = Logger.getLogger(AssertLoggedTextTest.class.getName());

	@Test
	public void testAssertLoggedText() {
		logger.info("This is a test.");
		Log4jAsserter.Assert.assertLogged("This is a test.");
	}

	@Test
	public void testAssertLoggedTextWithLevel() {
		logger.info("This is a test.");
		Log4jAsserter.Assert.assertLogged("This is a test.", Level.INFO);
	}

	@Test
	public void testAssertLoggedTextWithLevelAndLogger() {
		logger.info("This is a test.");
		Log4jAsserter.Assert.assertLogged("This is a test.", Level.INFO, AssertLoggedTextTest.class.getName());
	}
}
