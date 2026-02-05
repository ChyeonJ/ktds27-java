package com.ktdsuniversity.edu.fp.basic.impl;

import com.ktdsuniversity.edu.fp.basic.PrintSomething;
//PrintName의 NullPointer를 막기 위해서 Name2를 만들어서 널 체크함 => PrintName클래스는 내가 의도한 바가 맞으니 바꾸지 않겠다
//해서 클래스를 새롭게 만들어서 함
public class PrintName2 implements PrintSomething {

	@Override
	public void print(String message) {
		
		if (message == null) {
			System.out.println("출력할 내용이 없습니다.");
		}
		else {
			for(int i = 0; i < 5; i++) {
				System.out.println(message);
			}
		}
		
	}

}
