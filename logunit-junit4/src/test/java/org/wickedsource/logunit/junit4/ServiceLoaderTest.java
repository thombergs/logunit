package org.wickedsource.logunit.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.wickedsource.logunit.LogUnit;

public class ServiceLoaderTest {

	/**
	 * Tests if the LogUnit service for Log4J can be loaded properly.
	 */
	@Test
	public void testLoadService() {
		LogUnit logunit = LogUnit.get();
		Assert.assertNotNull(logunit);
		Assert.assertEquals(LogUnitForJUnit.class, logunit.getClass());
	}

}
