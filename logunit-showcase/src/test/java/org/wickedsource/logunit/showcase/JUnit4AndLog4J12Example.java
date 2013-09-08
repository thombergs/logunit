package org.wickedsource.logunit.showcase;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.wickedsource.logunit.LogLevel;
import org.wickedsource.logunit.LogUnit;

public class JUnit4AndLog4J12Example {

	private Logger logger = Logger.getLogger(JUnit4AndLog4J12Example.class);

	@Test
	public void test() {
		// retrieve the Log4J implementation of LogUnit
		LogUnit logunit = LogUnit.get();

		// use the expect* methods to define what you expect to be logged in
		// your unit test
		logunit.expect("An error occured!", LogLevel.ERROR);

		// produce an error message (in real code you would not do this
		// directly, but call some business method that does the logging)
		logger.error("An error occured!");

		// assert that the expectations defined earlier were actually fulfilled
		logunit.assertExpectations();
	}

}
