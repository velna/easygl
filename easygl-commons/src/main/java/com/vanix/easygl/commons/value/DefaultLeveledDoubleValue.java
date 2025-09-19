package com.vanix.easygl.commons.value;

public class DefaultLeveledDoubleValue extends AbstractDoubleValue implements LeveledDoubleValue {

	private final DoubleValue value;
	private final LimitedIntValue level;
	private final double[] values;

	public DefaultLeveledDoubleValue(LimitedIntValue level, double... values) {
		this.level = level;
		this.values = values;
		this.value = Value.of(values[level.get()]);
		this.level.addInterceptor((oldlevel, newLevel) -> {
			set(values[newLevel]);
			return newLevel;
		});
	}

	@Override
	public LimitedIntValue level() {
		return level;
	}

	@Override
	public double get(int level) {
		return values[level];
	}

	@Override
	public double get() {
		return value.get();
	}

	@Override
	public double doSet(double v) {
		return value.set(v);
	}

}
