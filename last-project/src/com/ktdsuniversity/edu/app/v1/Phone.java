package com.ktdsuniversity.edu.app.v1;

public class Phone {
	
	public static enum Type{
		PERSONAL, HOME, COMPANY 
		
	}
	
	//phone.class의 Type enum
	/**
	 * 분류타입
	 */
	private Phone.Type phoneType;
	/**
	 * 전화번호
	 */
	private String phoneNumber;
	
	public Phone(Phone.Type phoneType, String phoneNumber) {
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}

	public Phone.Type getPhoneType() {
		return phoneType;
	}
	
	public void setPhoneType(Phone.Type phoneType) {
		this.phoneType = phoneType;
	}
	
	public String getPhoneNunber() {
		return phoneNumber;
	}
	
	public void setPhoneNunber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "분류 : " + this.phoneType + "번호 : " + this.phoneNumber;
	}
	
}
