package com.ktdsuniversity.edu.generics.collections.list;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.mart.Product;

public class ListExam {
	
	public static void main(String[] args) {
		List<String> product = new ArrayList<>();
		product.add("최현종");
		product.add("최현종1");
		product.add("최현종23");
		product.add("최현종3");
		product.add("최현종54");
		product.add("최현종5");
		
		System.out.println(product);
		
		String name = null;
		for(int i = 0; i < product.size(); i++) {	//기존에 써왔던 반복문
			name = product.get(i);
			System.out.println(name);
		}
		
		//List도 마찬가지다
		for (String name2 : product) {
			System.out.println(name2);
		}
		
		
		int[] arr = new int[] {1,2,3};
		//for each => 앞으로 이것만 쓰냐? => 아니다, 특정 인덱스의 값이 필요하다면 for문을 써야하고, 인덱스가 필요 없다면 for each를 쓰면 된다
		for(int value : arr) {
			System.out.println(value);
		}
		
		String[] names2 = new String[] {"A","B","C"};
		/*
		 * 1. A	=> 이런식으로 넘버랑, 인덱스 값이 필요하면 for만 필요하다
		 * 2. B
		 * 3. C
		 */
		for (int i = 0; i < names2.length; i++) {
			System.out.println((i+1) + names2[i]);
		}
		
		
		//List<Product> products = new ArrayList<>();
	}
}