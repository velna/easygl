package com.vanix.easygl.commons.value;

public class DefaultIntValue extends AbstractIntValue implements IntValue {
	private int value;

	public DefaultIntValue(int value) {
		this.value = value;
	}

	@Override
	public int get() {
		return value;
	}

	protected int doSet(int v) {
		return value = v;
	}

}
