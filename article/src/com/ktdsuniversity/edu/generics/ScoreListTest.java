package com.ktdsuniversity.edu.generics;

import java.util.Arrays;

public class ScoreListTest<T> {	//<T>T말고도 ArrayType 이렇게 작성해 된다.
	
	public static void main(String[] args) {
		
		//Integer만 할당 가능함 int 안됨//노란 밑줄 발생 => ScoreList안에 타입을 넣어달라 T는 Object로 변결
		//ScoreList<Integer> list = new ScoreList<Integer>();	//Java 1.6 code
		
		ScoreList<Integer, Integer> list = new ScoreList<>(); //Java 1.7부터는 생성자 생성 후에 타입은 생략 가능
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);

		Reducer<Integer, Integer> listReducer = new Reducer<>() {

			@Override
			public Integer reduce(Integer input, Integer output) {
				return input + output;
			}
		};
		
		int sum = list.sum(listReducer, 0);
		System.out.println(sum);
		
		
//		int sum = 0;
//		for(int i = 0; i < 4; i++) {
//			sum += list.get(i);
//		}
//		System.out.println(sum);
		
		int value = list.get(0);
		System.out.println(value);
		
		ScoreList<String, String> strList = new ScoreList<>();
		strList.add("ㅁㄴ어ㅏㅣㅁ니ㅏㅇ");
		strList.add("ㅁㄴ어ㅏㅣㅁ니ㅏㅇ");
		String strValue = strList.get(0);
		System.out.println(strValue);
		
		String concat = "";
		for(int i = 0; i < 2; i++) {
			concat += strList.get(i);
		}
		System.out.println(concat);
		
		ScoreList<String[], String> arrayList = new ScoreList<>();
		arrayList.add(new String[] {"asjkdh","akjdshakj"});
		arrayList.add(new String[] {"asjkdh","akjdshakj"});
		String[] arrayValue = arrayList.get(0);
		System.out.println(Arrays.toString(arrayValue));

		Reducer<String[], String> arrayReducer = new Reducer<>() {

			@Override
			public String reduce(String[] input, String output) {
				for(int i = 0; i < input.length; i++) {
					output += input[i];
				}
				return output;
			}};
		
		String arrayResult = arrayList.sum(arrayReducer, " ");
		System.out.println(arrayReducer);
		
//		String arrayConcat = "";
//		for(int i = 0; i < 2; i++) {
//			arrayConcat += Arrays.toString(arrayList.get(i));
//		}
//		System.out.println(arrayConcat);
		
	} 
	
}
