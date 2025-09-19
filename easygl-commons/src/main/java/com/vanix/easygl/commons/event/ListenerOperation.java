package com.vanix.easygl.commons.event;

public interface ListenerOperation<T extends EventListener> {
	void subscribe(T listener);

	void unsubscribe(T listener);
}
