package com.jfixby.lambda.examples;

import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.red.desktop.ScarabeiDesktop;

public class FactorialMemoization {

	/*
	 * Factorial λ-function
	 */
	public static λFunction<Integer, Long> FACTORIAL = setupExpression();

	static final λFunction<Integer, Long> setupExpression() {
		return k -> {
			if (k < 0) {
				Err.reportError("k < 0");
			}
			if (k == 1) {
				Sys.sleep(1000);// Make it heavy function
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

	public static void main(String[] args) {

		ScarabeiDesktop.deploy();

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
