package com.wan.exotics.utils;

/**
 * <p>
 * *
 * Provides a class that can be used for capturing variables in an anonymous class implementation.
 *
 * @param <T>
 * @author Wan Clem
 */
public class Capture<T> {
    private T value;

    public Capture() {
    }

    public Capture(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
