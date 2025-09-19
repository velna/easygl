package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Random;

public class FloatLongValue extends DefaultLongValue {

	private final double floats;

	public FloatLongValue(long value, double floats) {
		super(value);
		this.floats = Math.abs(floats);
	}

	@Override
	public long get() {
		long base = super.get();
		long fvMax = (long) (base * floats);
		long fv = Random.nextLong((long) (fvMax << 1));
		fv -= fvMax;
		return base + fv;
	}

}
