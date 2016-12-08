package com.jfixby.lambda.examples;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.log.L;

public class FactorialUsualJava {

	/*
	 * Factorial λ-function
	 */
	public static λFunction<Integer, Long> FACTORIAL = setupExpression();

	static final λFunction<Integer, Long> setupExpression() {
		return new λFunction<Integer, Long>() {
			@Override
			public Long val(Integer input) {
				if (input < 0) {
					Err.reportError("k < 0");
				}
				if (input == 1) {
					return 1L;
				}
				if (input == 0) {
					return 1L;
				}
				final long result = input * FACTORIAL.val(input - 1);
				if (result < 0) {
					Err.reportError("Integer overflow");
				}
				return result;
			};
		};
	}

	public static void main(String[] args) {

		DesktopSetup.deploy();

		L.d("No memoization:");
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));

		/*
		 * λ-function with Memoization: stores the results of expensive function
		 * calls and returns the cached result when the same inputs occur again.
		 */
		FACTORIAL = Lambda.cache(FACTORIAL);
		L.d("Memoization enabled:");
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));

	}

}
