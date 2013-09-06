package org.wickedsource.logunit.junit4;

import org.junit.Assert;
import org.wickedsource.logunit.AbstractLogUnitAsserter;
import org.wickedsource.logunit.Expectation;

public class JUnitLogUnitAsserter extends AbstractLogUnitAsserter {

	@Override
	public void assertExpectations() {
		for (Expectation expectation : getExpectations()) {
			Assert.assertTrue(String.format("LogUnit expectation not fulfilled: %s",
					expectation), expectation.isFulfilled());
		}
	}

}
