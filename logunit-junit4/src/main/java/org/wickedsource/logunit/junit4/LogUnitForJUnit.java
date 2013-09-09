package org.wickedsource.logunit.junit4;

import java.util.Set;

import org.junit.Assert;
import org.wickedsource.logunit.Expectation;
import org.wickedsource.logunit.LogUnit;

/**
 * JUnit4 implementation of {@link LogUnit}.
 * 
 * @author Tom
 * 
 */
public class LogUnitForJUnit extends LogUnit {

	@Override
	public void assertExpectations(Set<Expectation> expectations) {
		for (Expectation expectation : expectations) {
			Assert.assertTrue(String.format(
					"LogUnit expectation not fulfilled: %s", expectation),
					expectation.isFulfilled());
		}
	}

}
