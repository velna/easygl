package com.vanix.easygl.commons.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class AbstractLongValue extends AbstractValue<LongInterceptor> implements LongValue {

	protected abstract long doSet(long v);

	@Override
	@EqualsAndHashCode.Include
	public abstract long get();

	@Override
	public long set(long v) {
		long newValue = v;
		if (null != interceptors) {
			long oldValue = get();
			long tmp;
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
