package com.jfixby.lambda.examples;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class FibonacciExample {

	/*
	 * Fibonacci λ-function
	 */
	public static λFunction<Integer, Long> FIBONACCI = setupExpression();

	static final λFunction<Integer, Long> setupExpression() {
		return k -> {
			L.d("", k);
			if (k < 0) {
				Err.reportError("k < 0");
			}
			if (k == 1) {
				return 1L;
			}
			if (k == 0) {
				return 0L;
			}
			final long result = FIBONACCI.val(k - 1) + FIBONACCI.val(k - 2);
			if (result < 0) {
				Err.reportError("Integer overflow");
			}
			return result;
		};
	}

	public static void main(String[] args) {
		DesktopAssembler.setup();

		/*
		 * λ-function with Memoization: stores the results of expensive function
		 * calls and returns the cached result when the same inputs occur again.
		 */
		FIBONACCI = Lambda.cache(FIBONACCI);

		for (int i = 0; i < 10; i++) {
			L.d("FIBONACCI(" + i + ")", FIBONACCI.val(i));
		}

	}

}
