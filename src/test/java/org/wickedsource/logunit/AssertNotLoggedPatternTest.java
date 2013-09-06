package org.wickedsource.logunit;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.junit.Test;

public class AssertNotLoggedPatternTest {

	Logger logger = Logger.getLogger(AssertNotLoggedPatternTest.class.getName());

	@Test
	public void testAssertNotLoggedPattern() {
		logger.info("This is a test.");

		Pattern pattern = Pattern.compile("is NOT a");
		LogUnit.Assert.assertNotLogged(pattern);
	}

	@Test
	public void testAssertNotLoggedPatternWithLevel() {
		logger.info("This is a test.");

		Pattern pattern = Pattern.compile("is a");
		LogUnit.Assert.assertNotLogged(pattern, Level.ERROR);
		LogUnit.Assert.assertNotLogged(pattern, Level.DEBUG);
		LogUnit.Assert.assertNotLogged(pattern, Level.TRACE);
		LogUnit.Assert.assertNotLogged(pattern, Level.FATAL);

		Pattern pattern2 = Pattern.compile("is NOT a");
		LogUnit.Assert.assertNotLogged(pattern2, Level.INFO);
		LogUnit.Assert.assertNotLogged(pattern2, Level.ERROR);
		LogUnit.Assert.assertNotLogged(pattern2, Level.DEBUG);
		LogUnit.Assert.assertNotLogged(pattern2, Level.TRACE);
		LogUnit.Assert.assertNotLogged(pattern2, Level.FATAL);
	}

	@Test
	public void testAssertNotLoggedPatternWithLevelAndLogger() {
		logger.info("This is a test.");

		Pattern pattern = Pattern.compile("is a");
		LogUnit.Assert.assertNotLogged(pattern, Level.ERROR, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern, Level.DEBUG, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern, Level.TRACE, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern, Level.FATAL, AssertNotLoggedPatternTest.class.getName());

		Pattern pattern2 = Pattern.compile("is NOT a");
		LogUnit.Assert.assertNotLogged(pattern2, Level.INFO, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern2, Level.ERROR, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern2, Level.DEBUG, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern2, Level.TRACE, AssertNotLoggedPatternTest.class.getName());
		LogUnit.Assert.assertNotLogged(pattern2, Level.FATAL, AssertNotLoggedPatternTest.class.getName());

		Pattern pattern2 = Pattern.compile("is a");
		LogUnit.Assert.assertNotLogged(pattern2, Level.INFO, "some Other Logger");
		LogUnit.Assert.assertNotLogged(pattern2, Level.ERROR, "some Other Logger");
		LogUnit.Assert.assertNotLogged(pattern2, Level.DEBUG, "some Other Logger");
		LogUnit.Assert.assertNotLogged(pattern2, Level.TRACE, "some Other Logger");
		LogUnit.Assert.assertNotLogged(pattern2, Level.FATAL, "some Other Logger");
	}

}
