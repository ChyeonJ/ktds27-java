package com.ktdsuniversity.edu.generics.collections.map;

public class Student {
	
	private String name;
	private int number;
	
	public Student(int number, String name) {
		this.number = number;
		this.name = name;
	}
	
	
	//return값 자동 생성 alt + s 3번
	@Override
	public String toString() {
		return "Student [name=" + name + ", number=" + number + "]";
	}
	
}
