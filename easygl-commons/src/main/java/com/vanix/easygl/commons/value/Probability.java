package com.vanix.easygl.commons.value;

import com.vanix.easygl.commons.Limited;
import com.vanix.easygl.commons.Random;

import java.util.function.Supplier;

public interface Probability extends DoubleValue, Limited<DoubleValue> {

	double NEVER = 0;

	double ALWAYS = 100;

	Probability RO_NEVER = new DefaultProbability(NEVER, true);

	Probability RO_ALWAYS = new DefaultProbability(ALWAYS, true);

	@Override
	default void limit(DoubleValue min, DoubleValue max) {
		double minV = min.get();
		double maxV = max.get();
		if (minV < NEVER || maxV > ALWAYS || minV > maxV) {
			throw new IllegalArgumentException(String.format("Illegal min(%s) or max(%s)", min, max));
		}
		min().set(minV);
		max().set(maxV);
	}

	default Probability multiply(double... probabilities) {
		double ret = get();
		for (double p : probabilities) {
			ret = ret * limit(p) / 100.0;
		}
		set((int) ret);
		return this;
	}

	default Probability add(double... probabilities) {
		double ret = get();
		for (double p : probabilities) {
			ret += limit(p);
		}
		set(ret);
		return this;
	}

	private boolean hasChance(double value) {
		return value >= min().get() && (value >= max().get() || Random.nextDouble(max().get()) < value);
	}

	default void takeChance(Runnable runnable) {
		if (hasChance(get())) {
			runnable.run();
		}
	}

	default void takeChance(Runnable runnable, Runnable noluck) {
		if (hasChance(get())) {
			runnable.run();
		} else {
			noluck.run();
		}
	}

	default <T> T takeChance(Supplier<T> runnable) {
		return hasChance(get()) ? runnable.get() : null;
	}

	default <T> T takeChance(Supplier<T> runnable, Supplier<T> noluck) {
		return hasChance(get()) ? runnable.get() : noluck.get();
	}

	static double limit(double p) {
		return Limited.limit(p, NEVER, ALWAYS);
	}

	static Probability of(double value) {
		return new DefaultProbability(value);
	}

	static Probability leveled(LimitedIntValue level, double... values) {
		return new LeveledProbability(level, values);
	}

	static Probability never() {
		return new DefaultProbability(NEVER);
	}

	static Probability always() {
		return new DefaultProbability(ALWAYS);
	}

	static Probability multiply(Probability... probabilities) {
		if (null == probabilities || probabilities.length == 0) {
			return never();
		}
		Probability ret = null;
		for (var p : probabilities) {
			if (ret == null) {
				ret = of(p.get());
			} else {
				ret.multiply(p.get());
			}
		}
		return ret;
	}

	static Probability add(Probability... probabilities) {
		if (null == probabilities || probabilities.length == 0) {
			return never();
		}
		Probability ret = null;
		for (var p : probabilities) {
			if (ret == null) {
				ret = of(p.get());
			} else {
				ret.add(p.get());
			}
		}
		return ret;
	}

}
