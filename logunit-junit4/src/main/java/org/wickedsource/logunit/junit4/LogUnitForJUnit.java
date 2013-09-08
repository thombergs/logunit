package org.wickedsource.logunit.junit4;

import org.junit.Assert;
import org.wickedsource.logunit.LogUnit;
import org.wickedsource.logunit.Expectation;

public class LogUnitForJUnit extends LogUnit {

	@Override
	public void assertExpectations() {
		for (Expectation expectation : getExpectations()) {
			Assert.assertTrue(String.format("LogUnit expectation not fulfilled: %s",
					expectation), expectation.isFulfilled());
		}
	}

}
