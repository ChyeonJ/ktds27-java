package com.ktdsuniversity.edu.app.v1;
/**
 * VO 클래스
 */
public class Company {
	
	/**
	 * 근무중인 회사의 이름
	 */
	private String companyName;
	/**
	 * 직급 (사원, 대리, 과장, 차장, 부장, 이사, 대표)
	 */
	private String job;
	/**
	 * 근무중인 회사의 주소
	 */
	private String address;
	
	public Company(String companyName, String job, String adderss) {
		this.companyName = companyName;
		this.job = job;
		this.address = adderss;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "사명 : " + this.companyName + "직급 : " + this.job + "주소 : " + this.address;
	}
	
}
