package com.vanix.easygl.commons.value;

public class DefaultLeveledLongValue extends AbstractLongValue implements LeveledLongValue {

	private final LongValue value;
	private final LimitedIntValue level;
	private final long[] values;

	public DefaultLeveledLongValue(LimitedIntValue level, long... values) {
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
	public long get(int level) {
		return values[level];
	}

	@Override
	public long get() {
		return value.get();
	}

	@Override
	public long doSet(long v) {
		return value.set(v);
	}

}
