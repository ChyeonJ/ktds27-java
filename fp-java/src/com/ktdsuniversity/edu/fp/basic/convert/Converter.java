package com.ktdsuniversity.edu.fp.basic.convert;

import java.util.function.Function;

public class Converter {
	
	//Stream 함수 => Function(인트 반환)
	public void printConvertResult2(String str, Function<String, Integer> function) {
		
		int result = function.apply(str);
		System.out.println(result);
		
	}
	
	public void printConvertResult(String str, Changer changer) {
		
		int result = changer.changeToInt(str);
		System.out.println(result);
	}
	
}
