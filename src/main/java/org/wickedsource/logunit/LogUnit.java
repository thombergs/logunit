package org.wickedsource.logunit;

import java.util.Set;

public class LogUnit {

	/**
	 * Retrieves the singleton implementation of {@link AbstractLogUnitAsserter} containing all assertion methods. The instance
	 * will be located automatically, depending on which implementation of {@link AbstractLogUnitAsserter} is contained within
	 * the classpath. If there is more than one implementation class contained within the classpath, an
	 * {@link IllegalStateException} will be thrown.
	 */
	public static AbstractLogUnitAsserter get() {
		try {
			ClassFinder classFinder = new ClassFinder();
			Set<Class<?>> classes = classFinder.getClasses(LogUnit.class.getClassLoader(), "org.wickedsource.logunit.junit4");
			Object asserter = null;
			for (Class<?> clazz : classes) {
				if (AbstractLogUnitAsserter.class.isAssignableFrom(clazz) && (clazz != AbstractLogUnitAsserter.class)) {
					if (asserter != null) {
						throw new IllegalStateException(
								"More than one implementation of %s found! Please check that you have only one LogUnit module in your classpath.");
					} else {
						asserter = clazz.newInstance();
					}
				}
			}
			return (AbstractLogUnitAsserter) asserter;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

}
