package com.ktdsuniversity.edu.fp.basic;

import com.ktdsuniversity.edu.fp.basic.impl.CallAge;
import com.ktdsuniversity.edu.fp.basic.impl.PrintName;

public class CallFunction {
	
	public void callInterface(PrintSomething ps) {
		String something = "반갑습니다";
		ps.print(something);
	}
	
	public void callAbstractCalss(CallSomething cs) {
		String something = "123";
		int result = cs.call(something);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		
		CallFunction cf = new CallFunction();
		cf.callInterface(new PrintName());
		cf.callAbstractCalss(new CallAge());
		
		
		//익명 클래스를 이용해서 기능을 구현함 => 익명클래스를 사용하지 않을 떄 클래스가 많아짐
		cf.callInterface(new PrintSomething() {

			@Override
			public void print(String message) {
				System.out.println(message);
				if(message != null) {
					System.out.println(message + "는 " + message.length() + "글자 입니다.");
				}
			}});
		cf.callAbstractCalss(new CallSomething() {

			@Override
			public int call(String message) {
				if(message != null) {
					return message.length();
				}
				return 0;
			}});
		
		// 메소드만 전달. => 람다 => 함수형언어 2번 함수를 인자로 전달 코드
		cf.callInterface((String message) -> {System.out.println(message + "입니다.");});	//중괄호 있으니 반환이 없다
		cf.callInterface((String message) -> System.out.println(message + "입니다."));	//중괄호 없으니 반환이 된다
											//println이 void를 대체해 반환 값이 일치하면 오류가 나지 않는다
		//cf.callAbstractCalss((String message) -> {return 0;});	<= 추상클래스는 람다 사용X
		
		
		PrintSomething function = (String message) -> {
			if(message == null) {
				System.out.println("파라미터 잘못됨");
			}
			else {
				System.out.println(message.repeat(40));
			}
		};
		
		//function 자체에는 뭐가 있는지 출력해보자~
		System.out.println(function);
		
		cf.callInterface(function);
		
		
		
	}
	
}
