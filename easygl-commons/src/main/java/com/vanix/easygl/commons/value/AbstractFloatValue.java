package com.vanix.easygl.commons.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractFloatValue extends AbstractValue<FloatInterceptor> implements FloatValue {

	protected abstract float doSet(float v);

	@Override
	@EqualsAndHashCode.Include
	public abstract float get();

	@Override
	public float set(float v) {
		float newValue = v;
		if (null != interceptors) {
			float oldValue = get();
			float tmp;
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
