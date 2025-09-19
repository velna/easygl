package com.vanix.easygl.application.task;


import com.vanix.easygl.commons.Identified;

public interface Task extends Runnable, Identified<String> {
	long next();

	static Task once(String id, Runnable runnable) {
		return new RunnableTask(id, runnable) {
			private int n = 0;

			@Override
			public long next() {
				return n--;
			}

		};
	}

	static TimeoutTask timeout(String id, long timeout, Runnable runnable) {
		return new TimeoutTask(id, timeout, runnable);
	}

	static Task interval(String id, long interval, int executeCount, Runnable runnable) {
		return new RunnableTask(id, runnable) {
			private int n = executeCount;

			@Override
			public long next() {
				if (n == executeCount) {
					n--;
					return 0;
				}
				return n-- > 0 ? interval : -1;
			}

		};
	}

	static Task interval(String id, long interval, Runnable runnable) {
		return new RunnableTask(id, runnable) {
			private boolean first = true;

			@Override
			public long next() {
				if (first) {
					first = false;
					return 0;
				}
				return interval;
			}

		};
	}
}
