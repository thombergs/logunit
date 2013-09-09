package org.wickedsource.logunit.showcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.wickedsource.logunit.LogLevel;
import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.testng.LogUnitForTestNG;

public class TestNGAndLogbackExample {

	private Logger logger = LoggerFactory
			.getLogger(TestNGAndLogbackExample.class);

	@Test
	public void test() {
		// register the TestNG module for LogUnit (happens automatically if you
		// have exactly one module on the classpath)
		LogUnit.setInstance(new LogUnitForTestNG());

		// retrieve the Log4J implementation of LogUnit
		LogUnit logunit = LogUnit.get();

		// use the expect* methods to define what you expect to be logged in
		// your unit test
		logunit.expect("An error occured!", LogLevel.ERROR);

		// produce an error message (in real code you would not do this
		// directly, but call the code under test that does the logging)
		logger.error("An error occured!");

		// assert that the expectations defined earlier were actually fulfilled
		logunit.assertExpectations();
	}

}
