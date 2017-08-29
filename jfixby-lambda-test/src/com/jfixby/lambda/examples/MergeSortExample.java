
package com.jfixby.lambda.examples;

import java.util.Random;

import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class MergeSortExample {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		// setup
		final λFunctionCache<Collection<Integer>, Collection<Integer>> cache = Lambda.newArrayCache();

		final λFunction<Collection<Integer>, Collection<Integer>> MERGE_SORT = new DesktopMergeSort<>(cache);

		// test
		final int number_of_elements = 50;
		for (int test = 0; test < 100; test++) {
			final long seed = Sys.SystemTime().currentTimeMillis();
			final Collection<Integer> input = generateInput(number_of_elements, seed, 0, 9);
			L.d();
			L.d("---sorting------------------------------------------------------------------------------------");
			L.d("input ", input.toString());
			final Collection<Integer> sorted = MERGE_SORT.val(input);
			L.d();
			L.d("sorted", sorted.toString());

		}
		// MERGE_SORT.printCache();

	}

	private static EditableCollection<Integer> generateInput (final int number, final long seed, final long from, final long to) {
		final Random random = new Random(seed);
		final long segment = to - from + 1;
		final List<Integer> result = Collections.newList();
		for (int i = 0; i < number; i++) {
			final int random_value = (int)(from + FloatMath.floorDown(random.nextFloat() * segment));
			result.add(random_value);
		}
		// result.clear();
		// result.addAllArrayElements(new Integer[] { 1, 2, 3, 4, 5, 6 });
		return result;
	}

}
