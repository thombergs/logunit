package org.wickedsource.logunit.testng;

import java.util.Set;

import org.testng.Assert;
import org.wickedsource.logunit.Expectation;
import org.wickedsource.logunit.LogUnit;

/**
 * TestNG implementation of {@link LogUnit}.
 * 
 * @author Tom
 * 
 */
public class LogUnitForTestNG extends LogUnit {

	@Override
	public void assertExpectations(Set<Expectation> expectations) {
		for (Expectation expectation : expectations) {
			Assert.assertTrue(expectation.isFulfilled(), String.format(
					"LogUnit expectation not fulfilled: %s", expectation));
		}
	}

}
