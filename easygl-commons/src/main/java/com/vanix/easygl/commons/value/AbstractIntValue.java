package com.vanix.easygl.commons.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractIntValue extends AbstractValue<IntInterceptor> implements IntValue {

	protected abstract int doSet(int v);

	@Override
	@EqualsAndHashCode.Include
	public abstract int get();

	@Override
	public int set(int v) {
		int newValue = v;
		if (null != interceptors) {
			int oldValue = get();
			int tmp;
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
