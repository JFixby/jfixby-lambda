package com.jfixby.lambda.examples;

import java.math.BigInteger;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.desktop.DesktopAssembler;

public class SortExample {

	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger ZERO = BigInteger.ZERO;

	/*
	 * Fibonacci λ-function
	 */
	public static λFunction<BigInteger, BigInteger> FIBONACCI = setupExpression();

	static final λFunction<BigInteger, BigInteger> setupExpression() {
		return k -> {
			L.d("computing", k);
			if (k.compareTo(ZERO) < 0) { // k < 0
				Err.reportError("k < 0");
			}
			if (k.equals(ONE)) {
				return ONE;
			}
			if (k.equals(ZERO)) {
				return ONE;
			}
			final BigInteger m1 = k.subtract(ONE);// k-1
			final BigInteger m2 = m1.subtract(ONE);// k-2
			final BigInteger fm1 = FIBONACCI.val(m1);// F(k-1)
			final BigInteger fm2 = FIBONACCI.val(m2);// F(k-2)
			final BigInteger result = fm1.add(fm2); // F(k-1) + F(k-2)
			if (result.compareTo(ZERO) < 0) {// result < 0
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
