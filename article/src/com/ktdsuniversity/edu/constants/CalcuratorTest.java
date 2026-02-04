package com.ktdsuniversity.edu.constants;

public class CalcuratorTest {
	
	public static void main(String[] args) {
		Calculator calc = new Calculator(40, 20);
		System.out.println(calc.compute(Calculator.ADD));	//이렇게도 가능하지만
		System.out.println(calc.compute(Calculator.SUB));
		System.out.println(calc.compute(Calculator.MUL));
		System.out.println(calc.compute(Calculator.DIV));
		
		System.out.println(calc.compute2(Operator.ADD));	//상수인데 숫자를 직접입력해서 동작하게 만듦 => 코드의 의미를 파악하지못함
		System.out.println(calc.compute2(Operator.SUB));
		System.out.println(calc.compute2(Operator.MUL));
		System.out.println(calc.compute2(Operator.DIV));
		
		
	}
	
}
