package com.jfixby.lambda.examples;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class SortExample {

	public static λFunction<EditableCollection<Int>, EditableCollection<Int>> MERGE_SORT = setupExpression();

	static final λFunction<EditableCollection<Int>, EditableCollection<Int>> setupExpression() {
		return collection -> {
			int N = collection.size();
			if (N == 1) {
				return collection;
			}
			if (N == 2) {
				if (collection.getElementAt(0).value > collection.getElementAt(1).value) {
					final Int e0 = collection.removeElementAt(0);
					collection.add(e0);
				}
				return collection;
			}
			EditableCollection<Int> tail = collection.splitAt(N / 2);
			EditableCollection<Int> head = JUtils.newList();
			head.addAll(collection);

			head = MERGE_SORT.val(head);
			tail = MERGE_SORT.val(tail);

			collection.clear();
			while (head.size() > 0 && tail.size() > 0) {
				Int h0 = head.getElementAt(0);
				Int t0 = tail.getElementAt(0);
				if (h0.value > t0.value) {
					tail.removeElementAt(0);
					collection.add(t0);
				} else {
					head.removeElementAt(0);
					collection.add(h0);
				}
			}
			if (head.size() > 0) {
				collection.addAll(head);
			} else {
				collection.addAll(tail);
			}
			return collection;
		};
	}

	public static void main(String[] args) {

		DesktopAssembler.setup();

		EditableCollection<Int> input_Ints = JUtils.newList(newInt(1), newInt(3), newInt(2), newInt(1), newInt(4), newInt(0));
		input_Ints.print("input");
		EditableCollection<Int> sorted = MERGE_SORT.val(input_Ints);
		sorted.print("sorted");

	}

	private static Int newInt(int i) {
		return new Int(i);
	}

}
