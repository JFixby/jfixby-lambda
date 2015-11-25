package com.jfixby.lambda.examples;

import java.util.Spliterator;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class SortExample {

	public static λFunction<EditableCollection<Int>, EditableCollection<Int>> MERGE_SORT = setupExpression();

	static final λFunction<EditableCollection<Int>, EditableCollection<Int>> setupExpression() {
		return collection -> {
			Spliterator<Int> split = collection.spliterator();
			return null;
		};
	}

	public static void main(String[] args) {

		DesktopAssembler.setup();

		EditableCollection<Int> input_Ints = JUtils.newList(newInt(1), newInt(3), newInt(2), newInt(1), newInt(4), newInt(0));
		input_Ints.print("input_Ints");
		MERGE_SORT.val(input_Ints);

	}

	private static Int newInt(int i) {
		return new Int(i);
	}

}
