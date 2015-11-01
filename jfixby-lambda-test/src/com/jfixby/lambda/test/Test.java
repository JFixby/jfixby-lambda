package com.jfixby.lambda.test;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.lambda.api.Lambda;
import com.jfixby.lambda.api.Variable;
import com.jfixby.lambda.red.RedLambda;

public class Test {

	public static void main(String[] args) {
		DesktopAssembler.setup();
		Lambda.installComponent(new RedLambda());
		Variable<Float> x = Lambda.newVariable();
		Variable<Float> y = Lambda.newVariable();

	}
}
