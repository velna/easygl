package com.vanix.easygl.application.task;


public class OperatableTask implements Task {

	private enum Status {
		Suspended, Canceled, OK
	}

	private final Task task;
	private Status status = Status.OK;
	private long next;

	public OperatableTask(Task task) {
		this.task = task;
		next = task.next();
	}

	@Override
	public String id() {
		return task.id();
	}

	@Override
	public void run() {
		if (status == Status.OK) {
			task.run();
		}
	}

	@Override
	public long next() {
		switch (status) {
		case Canceled:
			return -1;
		case Suspended:
			return next;
		default:
			long ret = next;
			next = task.next();
			return ret;
		}
	}

	public boolean isCanceled() {
		return status == Status.Canceled;
	}

	public boolean isSuspended() {
		return status == Status.Suspended;
	}

	public void cancel() {
		status = Status.Canceled;
	}

	public void suspend() {
		checkNotCanceled();
		status = Status.Suspended;
	}

	public void resume() {
		checkNotCanceled();
		status = Status.OK;
	}

	private void checkNotCanceled() {
		if (status == Status.Canceled) {
			throw new IllegalStateException("Task already canceled.");
		}
	}
}
