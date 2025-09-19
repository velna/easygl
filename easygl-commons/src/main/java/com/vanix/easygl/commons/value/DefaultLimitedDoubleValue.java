package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Limited;

public class DefaultLimitedDoubleValue extends DefaultDoubleValue implements LimitedDoubleValue {

	private final DoubleValue min;
	private final DoubleValue max;

	public DefaultLimitedDoubleValue(double value, double min, double max) {
		super(Limited.limit(value, min, max));
		this.min = Value.of(min);
		this.max = Value.of(max);
	}

	@Override
	protected double doSet(double v) {
		return super.doSet(Limited.limit(v, min.get(), max.get()));
	}

	@Override
	public void limit(DoubleValue min, DoubleValue max) {
		this.min.set(min.get());
		this.max.set(max.get());
	}

	@Override
	public DoubleValue min() {
		return min;
	}

	@Override
	public DoubleValue max() {
		return max;
	}
}
