/**
 *   Copyright 2013 Tom Hombergs (tom.hombergs@gmail.com)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.wickedsource.logunit.testng;

import java.util.Set;

import org.testng.Assert;
import org.wickedsource.logunit.Expectation;
import org.wickedsource.logunit.LogUnit;

/**
 * TestNG implementation of {@link LogUnit}.
 * 
 * @author Tom Hombergs <tom.hombergs@gmail.com>
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
