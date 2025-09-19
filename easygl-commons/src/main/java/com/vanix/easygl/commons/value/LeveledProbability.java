package com.vanix.easygl.commons.value;

public class LeveledProbability extends DefaultLeveledDoubleValue implements Probability {

	private final DoubleValue min = Value.limited(NEVER, NEVER, ALWAYS);
	private final DoubleValue max = Value.limited(ALWAYS, NEVER, ALWAYS);

	public LeveledProbability(LimitedIntValue level, double... values) {
		super(level, values);
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
