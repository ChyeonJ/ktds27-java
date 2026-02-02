package com.ktdsuniversity.edu.generics.collections.airport;

import java.util.ArrayList;
import java.util.List;

public class AirGeneric<T> {
	
	private List<T> seatStatus;
	
	public AirGeneric() {
		this.seatStatus = new ArrayList<>();
	}
	
	public void addSeat(T info) {
		this.seatStatus.add(info);
	}
}
