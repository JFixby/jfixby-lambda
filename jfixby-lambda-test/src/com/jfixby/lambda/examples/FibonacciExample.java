package com.jfixby.lambda.examples;

import java.math.BigInteger;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class FibonacciExample {

	/*
	 * Fibonacci λ-function
	 */
	public static λFunction<BigInteger, BigInteger> FIBONACCI = setupExpression();

	static final λFunction<BigInteger, BigInteger> setupExpression() {
		return k -> {
			L.d("computing", k);
			if (k.compareTo(BigInteger.ZERO) < 0) {
				Err.reportError("k < 0");
			}
			if (k.compareTo(BigInteger.ONE) == 0) {
				return BigInteger.ONE;
			}
			if (k.compareTo(BigInteger.ZERO) == 0) {
				return BigInteger.ZERO;
			}
			BigInteger m1 = k.subtract(BigInteger.ONE);
			BigInteger m2 = m1.subtract(BigInteger.ONE);
			final BigInteger result = FIBONACCI.val(m1).add(FIBONACCI.val(m2));
			if (result.compareTo(BigInteger.ZERO) < 0) {
				Err.reportError("Integer overflow");
			}
			// L.d("result", result);
			return result;
		};
	}

	public static void main(String[] args) {
		DesktopAssembler.setup();

		BigInteger input_value = new BigInteger("10");

		// No memoization: O(n^2) operations
		L.d("FIBONACCI(" + input_value + ")", FIBONACCI.val(input_value));

		/*
		 * λ-function with Memoization: stores the results of expensive function
		 * calls and returns the cached result when the same inputs occur again.
		 * O(n) operations
		 */
		FIBONACCI = Lambda.cache(FIBONACCI);
		L.d("FIBONACCI(" + input_value + ")", FIBONACCI.val(input_value));

	}

}
