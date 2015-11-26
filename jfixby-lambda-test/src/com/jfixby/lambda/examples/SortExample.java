package com.jfixby.lambda.examples;

import java.util.Random;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.desktop.DesktopAssembler;

@SuppressWarnings("rawtypes")
public class SortExample {

	public static λFunction<Collection<Comparable>, Collection<Comparable>> MERGE_SORT = setupExpression();

	@SuppressWarnings("unchecked")
	static final λFunction<Collection<Comparable>, Collection<Comparable>> setupExpression() {
		return input_list -> {
			int N = input_list.size();
			if (N == 1) {
				return input_list;
			}
			if (N == 2) {
				Comparable e0 = input_list.getElementAt(0);
				Comparable e1 = input_list.getElementAt(1);

				if (e0.compareTo(e1) > 0) {
					List<Comparable> result = JUtils.newList();// (2);
					result.add(input_list.getElementAt(1));
					result.add(input_list.getElementAt(0));
					return result;
				}
				return input_list;
			}
			Collection<Comparable> sorted_tail;
			Collection<Comparable> sorted_head;
			{
				List<Comparable> head = JUtils.newList();// N / 2
				List<Comparable> tail = JUtils.newList();// N - N / 2

				JUtils.arrayCopy(input_list, 0, head, N / 2);
				JUtils.arrayCopy(input_list, N / 2, tail, N - N / 2);

				System.out.print("x");// weight indicator
				sorted_tail = MERGE_SORT.val(tail);
				sorted_head = MERGE_SORT.val(head);
			}
			int head_pointer = 0;
			int tail_pointer = 0;

			List<Comparable> result = JUtils.newList();// N

			while (head_pointer < sorted_head.size() && tail_pointer < sorted_tail.size()) {
				Comparable h0 = sorted_head.getElementAt(head_pointer);
				Comparable t0 = sorted_tail.getElementAt(tail_pointer);
				if (h0.compareTo(t0) > 0) {
					result.add(t0);
					tail_pointer++;
				} else {
					result.add(h0);
					head_pointer++;
				}

			}

			if (head_pointer == sorted_head.size()) {
				JUtils.arrayCopy(sorted_tail, tail_pointer, result, sorted_tail.size() - tail_pointer);
			} else {
				JUtils.arrayCopy(sorted_head, head_pointer, result, sorted_head.size() - head_pointer);
			}
			return result;
		};

	}

	public static void main(String[] args) {

		DesktopAssembler.setup();

		λFunctionCache<Collection<Comparable>, Collection<Comparable>> cache = Lambda.newArrayCache();
		MERGE_SORT = Lambda.cache(MERGE_SORT, cache);

		int number_of_elements = 50;
		for (int test = 0; test < 100; test++) {
			long seed = Sys.SystemTime().currentTimeMillis();
			EditableCollection<Comparable> input = generateInput(number_of_elements, seed, 0, 9);
			L.d();
			L.d("---sorting------------------------------------------------------------------------------------");
			sort(input);

		}
		cache.print("cache");

	}

	private static EditableCollection<Comparable> generateInput(int number, long seed, long from, long to) {
		Random random = new Random(seed);
		long segment = to - from + 1;
		List<Comparable> result = JUtils.newList();
		for (int i = 0; i < number; i++) {
			long random_value = from + FloatMath.floorDown(random.nextFloat() * segment);
			// result.add(new Int(random_value));
			result.add(random_value);
		}
		// result.clear();
		// result.addAllArrayElements(new Integer[] { 1, 2, 3, 4, 5, 6 });
		return result;
	}

	private static void sort(Collection<Comparable> input) {
		L.d("input ", input.toString());
		Collection<Comparable> sorted = MERGE_SORT.val(input);
		L.d();
		L.d("sorted", sorted.toString());
	}

}
