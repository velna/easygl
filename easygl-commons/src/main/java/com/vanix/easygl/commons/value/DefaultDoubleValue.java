package com.vanix.easygl.commons.value;

public class DefaultDoubleValue extends AbstractDoubleValue implements DoubleValue {
	private double value;

	public DefaultDoubleValue(double value) {
		this.value = value;
	}

	@Override
	public double get() {
		return value;
	}

	protected double doSet(double v) {
		return value = v;
	}

}
