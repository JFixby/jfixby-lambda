
package com.jfixby.red.lambda;

import java.util.HashMap;

import com.jfixby.cmns.api.lambda.λFunctionCache;

public class MapGraph<X, Y> implements λFunctionCache<X, Y> {

	final HashMap<X, Y> mapping = new HashMap<>();

	@Override
	public Y get (final X value_number) {
		return this.mapping.get(value_number);
	}

	@Override
	public void put (final X value_number, final Y value) {
		this.mapping.put(value_number, value);
	}
}
