package com.vanix.easygl.commons;

public interface Limited<T> {

	void limit(T min, T max);

	T min();

	T max();

	static int limit(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}

	static long limit(long value, long min, long max) {
		return Math.max(min, Math.min(max, value));
	}

	static double limit(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}

	static float limit(float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}
}