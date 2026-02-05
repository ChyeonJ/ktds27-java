package com.ktdsuniversity.edu.fp.basic.convert;

public class Test {
	
	public static void main(String[] args) {
		
		Converter converter = new Converter();
		
		// 숫자로 변환한 결과를 반환한다
		converter.printConvertResult2("10", str -> Integer.parseInt(str));
		
		//static 메소드이고, 파라미터 타입과 개수, 반환 타입이 일치하면 쓸 수 있다
		converter.printConvertResult2("12", Integer::parseInt);
		
		//파라미터의 타입을 따라간다 => 이때는 파라미터의 개수가 중요한게 아니라 타입이 중요해진다.
		// 인스턴스 객체를 쓸때
		converter.printConvertResult2("asjdkdl", str -> str.length());
		converter.printConvertResult2("asjdkdl", String::length);
		
	}

}
