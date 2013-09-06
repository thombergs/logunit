package org.wickedsource.logunit;

import org.junit.Assert;
import org.junit.Test;
import org.wickedsource.logunit.log4j.Log4jAsserter;

public class LogUnitLocatorTest {

	@Test
	public void testLocate() {
		LogUnitAsserter asserter = LogUnit.get();
		Assert.assertNotNull(asserter);
		Assert.assertTrue(asserter instanceof Log4jAsserter);
	}

}
