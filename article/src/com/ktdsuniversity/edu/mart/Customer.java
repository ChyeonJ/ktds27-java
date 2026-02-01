package com.ktdsuniversity.edu.mart;
/**
 * 구매자 클래스
 * 소지금, 누적 포인트, 등급 관리
 */
public class Customer {
	

	private int money;
	private int point;
	private String grade;
	
	/**
	 * 
	 * @param money 돈
	 * @param point 포인트
	 * @param grade 등급
	 */
	public Customer(int money, int point, String grade) {
		this.money = money;
		this.point = point;
		this.grade = grade;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
