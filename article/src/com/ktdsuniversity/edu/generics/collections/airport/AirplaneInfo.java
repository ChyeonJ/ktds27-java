package com.ktdsuniversity.edu.generics.collections.airport;

public class AirplaneInfo {
	

	private int seatNum;
	private String seatStatus;
	
	public AirplaneInfo(int seatNum, String seatStatus) {
		this.seatNum = seatNum;
		this.seatStatus = seatStatus;
	}
	public int getSeatNum() {
		return seatNum;
	}
	
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	public String getSeatStatus() {
		return seatStatus;
	}
	
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	@Override
	public String toString() {
		return "airplaneInfo [seatNum=" + seatNum + ", seatStatus=" + seatStatus + "]";
	}
	
}
