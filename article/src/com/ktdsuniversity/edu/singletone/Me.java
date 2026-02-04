package com.ktdsuniversity.edu.singletone;

public class Me {
	
	// 전역 => static
	private static Me me;
	
	private String name;

	//생성자를 가려놓아 쓸수 없음 =>즉 인스턴스화를 하지 못함
	public Me() {
		this.name = "최현종";
	}
	
	//static을(synchronized) 붙이는 이유 => 
	public static Me getInstance() {
		
		if(Me.me == null) {
			Me.me = new Me();
		}
		
		return Me.me;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
