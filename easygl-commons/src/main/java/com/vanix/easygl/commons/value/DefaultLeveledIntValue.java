package com.vanix.easygl.commons.value;

public class DefaultLeveledIntValue extends AbstractIntValue implements LeveledIntValue {

	private final IntValue value;
	private final LimitedIntValue level;
	private final int[] values;

	public DefaultLeveledIntValue(LimitedIntValue level, int... values) {
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
	public int get(int level) {
		return values[level];
	}

	@Override
	public int get() {
		return value.get();
	}

	@Override
	public int doSet(int v) {
		return value.set(v);
	}

}
