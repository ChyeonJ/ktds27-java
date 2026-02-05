package com.ktdsuniversity.edu.fp.basic.calc;

public class Test {
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		
		// num1, num2를 더해 반환한다.
		int result = calc.calc2(1, 2, (num1, num2) -> num1 + num2);
		System.out.println(result);
		
		// num1의 num2를 제곱한 수를 반환
		result = calc.calc2(2,2, (num1, num2) -> (int) Math.pow(num1, num2));
		System.out.println(result);
		
		/* Method Reference Math의 원형을 가져다 쓴다 단, 형태가 같아야 한다. int, int retrunt int 이렇게  */
		result = calc.calc2(15, 2, Math::powExact);//위의 pow는 double double인데 powExact는 int int다
		System.out.println(result);
		
		// num1, num2 중 큰 수를 반환
		result = calc.calc2(15, 2, (num1, num2) -> Math.max(num2, num1));
		System.out.println(result);
		
		/* Method Reference Math의 min의 원형을 가져다 쓴다 단, 형태가 같아야 한다. int, int retrunt int 이렇게  */
		result = calc.calc2(15, 2, Math::min);
		System.out.println(result);
		
		// num1, num2 중 작은 수를 반환
		result = calc.calc2(15, 2, (num1, num2) -> Math.min(num2, num1));
		System.out.println(result);
		
		// num1이 num2의 배수라면 0을 반환, 아니라면 1을 반환
		result = calc.calc2(18, 2, (num1, num2) -> {
			
			if(num1 % num2 == 0) {
				return 0;
			}
			
			return 1;
		});
		System.out.println(result);
		
	}
	
}
