package com.ktdsuniversity.edu.app.test;

import java.util.ArrayList;
import java.util.List;

public class Lists {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		list.add(10);
		list.add(10);
		list.add(10);
		
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
		}
		
		System.out.println(sum);
		
		
	}
	
}
