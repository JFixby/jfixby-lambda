package com.jfixby.lambda.examples;

import java.util.Random;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.red.desktop.DesktopSetup;

public class MergeSortExample {

	public static void main(String[] args) {
		DesktopSetup.deploy();
		// setup
		λFunctionCache<Collection<Integer>, Collection<Integer>> cache = Lambda.newArrayCache();

		λFunction<Collection<Integer>, Collection<Integer>> MERGE_SORT = new DesktopMergeSort<Integer>(cache);

		// test
		int number_of_elements = 50;
		for (int test = 0; test < 100; test++) {
			long seed = Sys.SystemTime().currentTimeMillis();
			Collection<Integer> input = generateInput(number_of_elements, seed, 0, 9);
			L.d();
			L.d("---sorting------------------------------------------------------------------------------------");
			L.d("input ", input.toString());
			Collection<Integer> sorted = MERGE_SORT.val(input);
			L.d();
			L.d("sorted", sorted.toString());

		}
		// MERGE_SORT.printCache();
		cache.print("cache");

	}

	private static EditableCollection<Integer> generateInput(int number, long seed, long from, long to) {
		Random random = new Random(seed);
		long segment = to - from + 1;
		List<Integer> result = Collections.newList();
		for (int i = 0; i < number; i++) {
			int random_value = (int) (from + FloatMath.floorDown(random.nextFloat() * segment));
			result.add(random_value);
		}
		// result.clear();
		// result.addAllArrayElements(new Integer[] { 1, 2, 3, 4, 5, 6 });
		return result;
	}

}
