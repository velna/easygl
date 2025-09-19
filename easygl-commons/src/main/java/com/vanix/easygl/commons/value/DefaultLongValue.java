package com.vanix.easygl.commons.value;

public class DefaultLongValue extends AbstractLongValue implements LongValue{
	private long value;

	public DefaultLongValue(long value) {
		this.value = value;
	}

	@Override
	public long get() {
		return value;
	}

	protected long doSet(long v) {
		return value = v;
	}

}
