
package com.jfixby.red.lambda;

import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;

public class ArrayCache<Q, T> implements λFunctionCache<Collection<Q>, T> {
	private float max_array_size;

	public ArrayCache (final float max_array_size) {
		this.max_array_size = max_array_size;
	}

	public ArrayCache () {
		this(Float.MAX_VALUE);
	}

	final Map<List<Q>, T> mapping = Collections.newMap();

	@Override
	public T get (final Collection<Q> key) {
		if (this.max_array_size < key.size()) {
			return null;
		}
		final List<Q> input = this.toKey(key);
		final T cached = this.mapping.get(input);
		if (cached != null) {
			return cached;
		}
		return null;
	}

	private List<Q> toKey (final Collection<Q> key) {
		return Collections.newList(key);
	}

	@Override
	public void put (final Collection<Q> key, final T value) {
		if (this.max_array_size < key.size()) {
			return;
		}
		final List<Q> input = this.toKey(key);
		this.mapping.put(input, value);
	}

}
