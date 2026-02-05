package com.ktdsuniversity.edu.fp.basic.kakao;

import java.time.LocalDate;

public class Friend {
	
	private String name;

	private LocalDate birtadte;
	
	public Friend(String name, String birthdate) {
		this.name = name;
		this.birtadte = LocalDate.parse(birthdate);
		
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getBirtadte() {
		return this.birtadte;
	}
	
	@Override
	public String toString() {
		return "이름 : " + this.name + ", 생일 : " + this.birtadte;
	}
	
}
