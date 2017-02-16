package com.jfixby.lambda.test;

import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;

public class CompositionTest {

	public static void main(String[] args) {

		ScarabeiDesktop.deploy();

		λFunction<Integer, Long> f = x -> x + 1L;
		λFunction<Long, String> g = x -> (x - 1L) + "x";
		λFunction<Integer, String> h = Lambda.compose(g, f);

		L.d("H(0)", h.val(0));

	}

}
