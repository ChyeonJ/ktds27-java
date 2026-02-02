package com.ktdsuniversity.edu.exceptions;

import com.ktdsuniversity.edu.exceptions.custom.DevideZeroException;
import com.ktdsuniversity.edu.exceptions.custom.WrongOperatorException;
import com.ktdsuniversity.edu.exceptions.custom.nullOperatorException;

public class SimpleCalcurator {
	/**
	 * 계산기
	 * @param a 계산할 값
	 * @param b 계산할 값
	 * @param operator 연산자 (+, -, *, /)
	 * @return 연산자에 따른 결과를 반환.
	 */
	public int calc(int a, int b, String operator) {
		int result = 0;
		
		if(operator == null) {
			//null이 나온다면 일부러 바로 죽여버린다.
			throw new nullOperatorException("null은 안된단다");
			//이 밑은 데드코드다 절대로 동작할 수 없는 공간이다.
		}
		
		if(operator.equals("+")) {
			result = a + b;
		}
		else if(operator.equals("-")) {
			result = a - b;
		}
		else if(operator.equals("*")) {
			result = a * b;
		}
		else if(operator.equals("/")) {
			if(a == 0 ||b == 0) {
				throw new DevideZeroException("0은 못쓴단다" + b);
//				System.out.println("0은 못쓴단다" + b);
//				return 0;
			}
			result = a / b;
		}
		else {
			throw new WrongOperatorException("잘못된 연산자 입니다.");
//			System.out.println("잘못된 연산자 입니다.");
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		SimpleCalcurator sc = new SimpleCalcurator();
		int result = 0;
		
		// 덧셈
		try {
		result = sc.calc(10,0,"+");
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}

		// 뺼셈
		try {
		result = sc.calc(10,0,"-");
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}	
		System.out.println(result);
		
		// 곱셈
		try {
		result = sc.calc(10,0,"*");
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}
		System.out.println(result);
		
		//나눗셈
		try {
		result = sc.calc(10,0,"/");//java.lang.ArithmeticException => 0으로 나누려고 하면 출력되는 에러 메시지
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}
		System.out.println(result);
		
		//잘못된 케이스 1
		try {
		result = sc.calc(10,0,"%");
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}
		System.out.println(result);
		
		//잘못된 케이스 2
		try {
		result = sc.calc(10,0,null);
		System.out.println(result);
		}
		catch(nullOperatorException noe) {	//연산자가 null일 때 처리
			System.out.println("연산자가 비어있습니다. 연산자를 채워서 다시 실행하세요");
		}
		catch(DevideZeroException dze) {	//0으로 나누려고 하면 처리
			System.out.println("0으로 나누려 했습니다. 숫자를 제대로 작성해서 다시 실행하세요");
		}
		catch (WrongOperatorException woe) {	//연산자를 잘못작성 했을 떄의 처리
			System.out.println("지원하지 않는 연산자입니다. +, -, *, / 중에 선택해서 다시 실행하세요.");
		}
		System.out.println(result);
		
		
	}
	
}
