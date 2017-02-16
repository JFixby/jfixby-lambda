
package com.jfixby.lambda.examples;

import java.math.BigInteger;

import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.red.lambda.RedLambda;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;

public class FibonacciExample {

	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger ZERO = BigInteger.ZERO;

	/*
	 * Fibonacci λ-function
	 */
	public static λFunction<BigInteger, BigInteger> FIBONACCI = setupExpression();

	static final λFunction<BigInteger, BigInteger> setupExpression () {
		return k -> {
			Debug.checkNull("input", k);

			L.d("computing", k);

			if (k.compareTo(ZERO) < 0) { // k < 0
				Err.reportError("k < 0 : " + k);
			}

			if (k.equals(ONE)) {
				return ONE;
			}

			if (k.equals(ZERO)) {
				return ZERO;
			}

			final BigInteger m1 = k.subtract(ONE);// k-1
			final BigInteger m2 = m1.subtract(ONE);// k-2
			final BigInteger fm1 = FIBONACCI.val(m1);// F(k-1)
			final BigInteger fm2 = FIBONACCI.val(m2);// F(k-2)
			final BigInteger result = fm1.add(fm2); // F(k-1) + F(k-2)

			// L.d("result", result);
			return result;
		};
	}

	public static void main (String[] args) {
		ScarabeiDesktop.deploy();
		Lambda.installComponent(new RedLambda());

		BigInteger input_value = new BigInteger(300 + "");

		/* No memoization: O(2^n) operations */

		// L.d("FIBONACCI(" + input_value + ")", FIBONACCI.val(input_value));

		/*
		 * λ-function with Memoization: stores the results of expensive function calls and returns the cached result when the same
		 * inputs occur again. O(n^2) operations
		 */
		FIBONACCI = Lambda.cache(FIBONACCI);
		L.d("FIBONACCI(" + input_value + ")", FIBONACCI.val(input_value));

	}

}
