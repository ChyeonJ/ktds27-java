package com.ktdsuniversity.edu.app.v1;

public class Phone {
	
	public static enum Type{
		PERSONAL, HOME, COMPANY
	}
	
	private Phone.Type phoneType;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
