package org.wickedsource.logunit;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLog {

	private List<LogUnitEvent> events = new ArrayList<LogUnitEvent>();

	public void append(LogUnitEvent logEvent) {
		events.add(logEvent);
	}

	/**
	 * Returns the {@link InMemoryLog} instance that is attached to the current
	 * thread.
	 */
	public static InMemoryLog getThreadLocal() {
		ThreadLocal<InMemoryLog> threadLocal = new ThreadLocal<InMemoryLog>();
		InMemoryLog log = threadLocal.get();
		if (log == null) {
			log = new InMemoryLog();
			threadLocal.set(log);
		}
		return log;
	}

}
