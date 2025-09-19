package com.vanix.easygl.commons.value;

public class DefaultFloatValue extends AbstractFloatValue implements FloatValue {
	private float value;

	public DefaultFloatValue(float value) {
		this.value = value;
	}

	@Override
	public float get() {
		return value;
	}

	protected float doSet(float v) {
		return value = v;
	}

}
