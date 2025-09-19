package com.vanix.easygl.application.task;

public abstract class RunnableTask implements Task {
	private final Runnable runnable;
	private final String id;

	public RunnableTask(String id, Runnable runnable) {
		this.id = id;
		this.runnable = runnable;
	}

	@Override
	public void run() {
		runnable.run();
	}

	@Override
	public String id() {
		return id;
	}
}