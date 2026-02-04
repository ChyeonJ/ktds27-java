package com.ktdsuniversity.edu.datetime.mart;

public class Test {
	
	public static void main(String[] args) {
		Store it = new Store();
		
		it.add(new Item("서울우유", "2008-02-04"));
		it.add(new Item("서울우유", "2008-02-03"));
		it.add(new Item("서울우유", "2008-02-07"));
		
		it.sell(Status.TODAY);
		it.sell(Status.PAST);
		it.sell(Status.NOSELL);
	}

}
