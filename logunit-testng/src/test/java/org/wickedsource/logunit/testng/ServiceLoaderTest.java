package org.wickedsource.logunit.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.wickedsource.logunit.LogUnit;

public class ServiceLoaderTest {

	/**
	 * Tests if the LogUnit service for TestNG can be loaded properly.
	 */
	@Test
	public void testLoadService() {
		LogUnit logunit = LogUnit.get();
		Assert.assertNotNull(logunit);
		Assert.assertEquals(LogUnitForTestNG.class, logunit.getClass());
	}

}
