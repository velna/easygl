package com.vanix.easygl.commons.value;

public class DefaultLeveledFloatValue extends AbstractFloatValue implements LeveledFloatValue {

	private final FloatValue value;
	private final LimitedIntValue level;
	private final float[] values;

	public DefaultLeveledFloatValue(LimitedIntValue level, float... values) {
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
	public float get(int level) {
		return values[level];
	}

	@Override
	public float get() {
		return value.get();
	}

	@Override
	public float doSet(float v) {
		return value.set(v);
	}

}
