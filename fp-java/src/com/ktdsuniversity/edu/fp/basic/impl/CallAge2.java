package com.ktdsuniversity.edu.fp.basic.impl;

import com.ktdsuniversity.edu.fp.basic.CallSomething;

//NumberFormat Exception 발생을 막기위한 클래스 CallAge의 코드를 의도한 바이다 해서 클래스 새로 생성
public class CallAge2  extends CallSomething{

	@Override
	public int call(String message) {
		try {
			return Integer.parseInt(message);
		} catch (Exception e) {
			return 0;
		}
	}

}
