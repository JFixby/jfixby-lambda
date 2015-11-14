package com.jfixby.lambda.api;

public interface Variable<T> extends λFunction<T> {

	public void set(T value);

}
