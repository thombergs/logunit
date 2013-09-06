package org.wickedsource.logunit;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.junit.Test;

public class AssertLoggedPatternTest {

	Logger logger = Logger.getLogger(AssertLoggedPatternTest.class.getName());

	@Test
	public void testAssertLoggedPattern() {
		logger.info("This is a test.");
		Pattern pattern = Pattern.compile("is a");
		LogUnit.Assert.assertLogged(pattern);
	}

	@Test
	public void testAssertLoggedPatternWithLevel() {
		logger.info("This is a test.");
		Pattern pattern = Pattern.compile("is a");
		LogUnit.Assert.assertLogged(pattern, Level.INFO);
	}

	@Test
	public void testAssertLoggedPatternWithLevelAndLogger() {
		logger.info("This is a test.");
		Pattern pattern = Pattern.compile("is a");
		LogUnit.Assert.assertLogged(pattern, Level.INFO, AssertLoggedPatternTest.class.getName());
	}
}
