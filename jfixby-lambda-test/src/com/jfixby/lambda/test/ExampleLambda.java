package com.jfixby.lambda.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class ExampleLambda {

	// Calculating factorial using Java Lambda Expression

	public interface FunctionalInterface<Input, Output> {
		Output apply(Input input);
	}

	static FunctionalInterface<Long, Long> factorial = null;

	public static void main(String[] args) {
		DesktopAssembler.setup();

		factorial = n -> {
			if (n == 0) {
				return 1L;
			}
			return n * factorial.apply(n - 1);
		};

		L.d(factorial.apply(5L));

	}
}
