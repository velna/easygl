package com.vanix.easygl.commons.value;

public class DefaultProbability extends DefaultDoubleValue implements Probability {

	private final boolean readonly;

	private final DoubleValue min = Value.limited(NEVER, NEVER, ALWAYS);
	private final DoubleValue max = Value.limited(ALWAYS, NEVER, ALWAYS);

	public DefaultProbability(double v) {
		this(v, false);
	}

	public DefaultProbability(double v, boolean readonly) {
		super(v);
		this.readonly = readonly;
	}

	@Override
	protected double doSet(double v) {
		return readonly ? get() : super.doSet(Probability.limit(v));
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
