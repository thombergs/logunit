package org.wickedsource.logunit;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class AssertNotLoggedTextTest {

	Logger logger = Logger.getLogger(AssertNotLoggedTextTest.class.getName());

	@Test
	public void testAssertNotLoggedText() {
		logger.info("This is a test.");
		LogUnit.Assert.assertNotLogged("This is NOT a test.");
	}

	@Test
	public void testAssertNotLoggedTextWithLevel() {
		logger.info("This is a test.");
		LogUnit.Assert.assertNotLogged("This is a test.", Level.DEBUG);
		LogUnit.Assert.assertNotLogged("This is a test.", Level.ERROR);
		LogUnit.Assert.assertNotLogged("This is a test.", Level.WARNING);
		LogUnit.Assert.assertNotLogged("This is a test.", Level.FATAL);

		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.INFO);
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.DEBUG);
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.ERROR);
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.WARNING);
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.FATAL);
	}

	@Test
	public void testAssertNotLoggedTextWithLevelAndLogger() {
		logger.info("This is a test.");
		LogUnit.Assert.assertNotLogged("This is a test.", Level.DEBUG, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is a test.", Level.ERROR, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is a test.", Level.WARNING, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is a test.", Level.FATAL, AssertNotLoggedTextTest.class.getName());

		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.INFO, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.DEBUG, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.ERROR, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.WARNING, AssertNotLoggedTextTest.class.getName());
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.FATAL, AssertNotLoggedTextTest.class.getName());

		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.INFO, "some other logger");
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.DEBUG, "some other logger");
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.ERROR, "some other logger");
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.WARNING, "some other logger");
		LogUnit.Assert.assertNotLogged("This is NOT a test.", Level.FATAL, "some other logger");
	}
}
