
package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λFunctionCache;

public class EmptyGraph<X, Y> implements λFunctionCache<X, Y> {

	@Override
	public Y get (final X value_number) {
		return null;
	}

	@Override
	public void put (final X value_number, final Y value) {
	}
}
