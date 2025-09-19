package com.vanix.easygl.commons.value;

public interface Value<I> {

	void addInterceptor(I interceptor);

	void removeInterceptor(I interceptor);

	static IntValue of(int v) {
		return new DefaultIntValue(v);
	}

	static LongValue of(long v) {
		return new DefaultLongValue(v);
	}

	static FloatValue of(float v) {
		return new DefaultFloatValue(v);
	}

	static DoubleValue of(double v) {
		return new DefaultDoubleValue(v);
	}

	static LongValue floating(long base, double floats) {
		return new FloatLongValue(base, floats);
	}

	static LimitedIntValue positive(int v) {
		return new DefaultLimitedIntValue(v, 0, Integer.MAX_VALUE);
	}

	static LimitedLongValue positive(long v) {
		return new DefaultLimitedLongValue(v, 0, Long.MAX_VALUE);
	}

	static LimitedFloatValue positive(float v) {
		return new DefaultLimitedFloatValue(v, 0.0f, Float.MAX_VALUE);
	}

	static LimitedDoubleValue positive(double v) {
		return new DefaultLimitedDoubleValue(v, 0, Double.MAX_VALUE);
	}

	static LimitedIntValue limited(int v, int min, int max) {
		return new DefaultLimitedIntValue(v, min, max);
	}

	static LimitedLongValue limited(long v, long min, long max) {
		return new DefaultLimitedLongValue(v, min, max);
	}

	static LimitedFloatValue limited(float v, float min, float max) {
		return new DefaultLimitedFloatValue(v, min, max);
	}

	static LimitedDoubleValue limited(double v, double min, double max) {
		return new DefaultLimitedDoubleValue(v, min, max);
	}

	static LeveledIntValue leveled(LimitedIntValue level, int... values) {
		return new DefaultLeveledIntValue(level, values);
	}

	static LeveledLongValue leveled(LimitedIntValue level, long... values) {
		return new DefaultLeveledLongValue(level, values);
	}

	static LeveledFloatValue leveled(LimitedIntValue level, float... values) {
		return new DefaultLeveledFloatValue(level, values);
	}

	static LeveledDoubleValue leveled(LimitedIntValue level, double... values) {
		return new DefaultLeveledDoubleValue(level, values);
	}

}
