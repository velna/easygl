package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Limited;

public class DefaultLimitedLongValue extends DefaultLongValue implements LimitedLongValue {

	private final LongValue min;
	private final LongValue max;

	public DefaultLimitedLongValue(long value, long min, long max) {
		super(Limited.limit(value, min, max));
		this.min = Value.of(min);
		this.max = Value.of(max);
	}

	@Override
	protected long doSet(long v) {
		return super.doSet(Limited.limit(v, min.get(), max.get()));
	}

	@Override
	public void limit(LongValue min, LongValue max) {
		this.min.set(min.get());
		this.max.set(max.get());
	}

	@Override
	public LongValue min() {
		return min;
	}

	@Override
	public LongValue max() {
		return max;
	}
}
