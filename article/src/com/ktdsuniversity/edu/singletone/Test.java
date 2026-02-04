package com.ktdsuniversity.edu.singletone;

public class Test {
	
	public static void main(String[] args) {
		Me me = Me.getInstance();
		System.out.println(me);
		
		Me you = Me.getInstance();
		System.out.println(you);
		
		System.out.println(me == you);
		
		you.setName("ㅇㅇㅇㅇ");
		
		System.out.println(me.getName());
		System.out.println(you.getName());
	}

}
