package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.log.L;

public class CollectionGraph<X, Y> implements λFunctionCache<X, Y> {

	final Map<X, Y> mapping = Collections.newMap();

	@Override
	public void print(String tag) {

	}

	@Override
	public Y get(X value_number) {
		L.d("extracting", value_number);
		Y e = this.mapping.get(value_number);
		if (e == null) {
			L.d("    value " + value_number + " not found");
		} else {
			L.d("    loading" + value_number, e);
		}
		return e;
	}

	@Override
	public void put(X value_number, Y value) {
		L.d("caching " + value_number, value);
		this.mapping.put(value_number, value);
		mapping.print("chache");
	}
}
