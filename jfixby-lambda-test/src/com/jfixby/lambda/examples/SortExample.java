package com.jfixby.lambda.examples;

import java.util.Spliterator;

import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class SortExample {

	public static λFunction<EditableCollection<Comparable>, Void> MERGE_SORT = setupExpression();

	static final λFunction<EditableCollection<Comparable>, Void> setupExpression() {
		return collection -> {
			Spliterator<Comparable> split = collection.spliterator();
			return null;
		};
	}

	public static void main(String[] args) {

		DesktopAssembler.setup();

		EditableCollection<Comparable> input_integers = JUtils.newList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		MERGE_SORT.val(input_integers);

	}

}
