package com.vanix.easygl.commons.event;

public class Event<T> {
    private final T source;

    public Event(T source) {
        this.source = source;
    }

    public T source() {
        return source;
    }
}
