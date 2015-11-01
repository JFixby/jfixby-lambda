package com.jfixby.lambda.api;

public interface Variable<T> {

	public T evaluate();

	public void setValue(T value);

	public T getValue();

}
