package com.jfixby.lambda.test;

import com.jfixby.cmns.desktop.DesktopAssembler;

public class LambdaImage {

	public interface F {
		float value(int x, int y);
	}

	public interface A {
		F apply(F f);
	}

	public interface Fop {
		F apply(F a, F b);
	}

	public interface Aop {
		A apply(A a, A b);
	}

	public interface Aswitch {
		A value(boolean value);
	}

	public interface AVec {
		A index(int i);
	}

	public interface AUnitVector {
		AVec index(int k);
	}

	public interface AVecScalarOp {
		AVec apply(AVec vector, A k);
	}

	public interface AVecVectorOp {
		AVec apply(AVec a, AVec b);
	}

	public interface Operator {
		AVec apply(F f);
	}

	public interface AVecProjector {
		float value(AVec V);
	}

	public interface Aproperty {
		float value(A a);
	}

	public static void main(String[] args) {
		DesktopAssembler.setup();

		F FZero = (x, y) -> 0f;
		F FOne = (x, y) -> 1f;

		A dFdx = f -> ((x, y) -> (f.value(x + 1, y) - f.value(x - 1, y)) / 2f);
		A dFdy = f -> ((x, y) -> (f.value(x, y + 1) - f.value(x, y - 1)) / 2f);

		Fop FplusF = (a, b) -> ((x, y) -> (a.value(x, y) + b.value(x, y)));
		Fop FmuliplyF = (a, b) -> ((x, y) -> (a.value(x, y) * b.value(x, y)));

		A A0 = f -> FZero;
		A A1 = f -> FOne;

		// a: f->F
		// b: f->F
		Aop FFplusFF = (a, b) -> (f -> FplusF.apply(a.apply(f), b.apply(f)));
		Aop FFmultiplyFF = (a, b) -> (f -> FmuliplyF.apply(a.apply(f), b.apply(f)));

		Aswitch FnBoolean = flag -> {
			if (flag)
				return A1;
			return A0;
		};

		AUnitVector Ek = k -> (i -> FnBoolean.value(i == k));
		AVec E0 = Ek.index(0);
		AVec E1 = Ek.index(1);

		AVecScalarOp F2FVecMupliplyK = (v, k) -> (i -> FFmultiplyFF.apply(v.index(i), k));
		AVecVectorOp F2FVecSum = (a, b) -> (i -> FFplusFF.apply(a.index(i), b.index(i)));

		Operator gradient = f -> F2FVecSum.apply(F2FVecMupliplyK.apply(E0, dFdx), F2FVecMupliplyK.apply(E1, dFdy));

		F image = (x, y) -> (-1 * (cos(2 * x) + cos(2 * y)) * 2);
		AVec gradImage = gradient.apply(image);
		// L.d("sketchy", sketchy.toString());

		F gradX = dFdx.apply(image);

//		A gradX = gradImage.index(0);
//		A gradY = gradImage.index(1);

	}

	static float cos(double x) {
		return (float) Math.cos(x);
	}

	private static float abs(float value) {
		return Math.abs(value);
	}
}
