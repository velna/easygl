package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Limited;

public class DefaultLimitedIntValue extends DefaultIntValue implements LimitedIntValue {

	private final IntValue min;
	private final IntValue max;

	public DefaultLimitedIntValue(int value, int min, int max) {
		super(Limited.limit(value, min, max));
		this.min = Value.of(min);
		this.max = Value.of(max);
	}

	@Override
	protected int doSet(int v) {
		return super.doSet(Limited.limit(v, min.get(), max.get()));
	}

	@Override
	public void limit(IntValue min, IntValue max) {
		this.min.set(min.get());
		this.max.set(max.get());
	}

	@Override
	public IntValue min() {
		return min;
	}

	@Override
	public IntValue max() {
		return max;
	}
}
