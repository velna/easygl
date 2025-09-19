package com.vanix.easygl.commons.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractDoubleValue extends AbstractValue<DoubleInterceptor> implements DoubleValue {

	protected abstract double doSet(double v);

	@Override
	@EqualsAndHashCode.Include
	public abstract double get();

	@Override
	public double set(double v) {
		double newValue = v;
		if (null != interceptors) {
			double oldValue = get();
			double tmp;
			for (var i : this.interceptors) {
				tmp = newValue;
				newValue = i.apply(oldValue, newValue);
				oldValue = tmp;
			}
		}
		return doSet(newValue);
	}

	@Override
	public String toString() {
		return String.valueOf(get());
	}
}
