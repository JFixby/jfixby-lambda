package com.jfixby.lambda.test;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class MemoizationTest {

	/*
	 * Factorial λ-function
	 */
	static public λFunction<Integer, Long> FACTORIAL = setupExpression();

	public static void main(String[] args) {

		DesktopAssembler.setup();

		L.d("No memoization:");
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));

		/*
		 * λ-function with Memoization: stores the results of expensive function
		 * calls and returns the cached result when the same inputs occur again.
		 */
		FACTORIAL = Lambda.newFunction(FACTORIAL);
		L.d("Memoization enabled:");
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));
		L.d("FACTORIAL(5)", FACTORIAL.val(5));

	}

	private static λFunction<Integer, Long> setupExpression() {
		return k -> {
			if (k < 0) {
				Err.reportError("k < 0");
			}
			if (k == 1) {
				Sys.sleep(1000);
				return 1L;
			}
			if (k == 0) {
				return 1L;
			}
			final long result = k * FACTORIAL.val(k - 1);
			if (result < 0) {
				Err.reportError("Integer overflow");
			}
			return result;
		};
	}

}
