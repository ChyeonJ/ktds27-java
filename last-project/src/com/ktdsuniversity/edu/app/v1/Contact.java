package com.ktdsuniversity.edu.app.v1;

import java.util.ArrayList;
import java.util.List;

public class Contact {
	
	/**
	 * 이름
	 */
	private String name;

	/**
	 * 별명
	 */
	private String nickName;

	/**
	 * 이메일
	 */
	private String email;

	/**
	 * 성(Last name/Family name)을 제외하고 개인을 부르는 고유한 이름
	 */
	private String firstName;
	
	/**
	 * 영어 이름에서 성(姓)
	 */
	private String lastName;

	/**
	 * 메모
	 */
	private String memo;
	
	/**
	 * 전화번호 목록
	 */
	private List<Phone> phones;
	
	/**
	 * 회사 정보
	 */
	private Company company;
	
	
	public Contact() {
		this.phones = new ArrayList<Phone>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


}
