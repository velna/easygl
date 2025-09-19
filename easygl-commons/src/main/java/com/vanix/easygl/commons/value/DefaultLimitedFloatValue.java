package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Limited;

public class DefaultLimitedFloatValue extends DefaultFloatValue implements LimitedFloatValue {

	private final FloatValue min;
	private final FloatValue max;

	public DefaultLimitedFloatValue(float value, float min, float max) {
		super(Limited.limit(value, min, max));
		this.min = Value.of(min);
		this.max = Value.of(max);
	}

	@Override
	protected float doSet(float v) {
		return super.doSet(Limited.limit(v, min.get(), max.get()));
	}

	@Override
	public void limit(FloatValue min, FloatValue max) {
		this.min.set(min.get());
		this.max.set(max.get());
	}

	@Override
	public FloatValue min() {
		return min;
	}

	@Override
	public FloatValue max() {
		return max;
	}
}
