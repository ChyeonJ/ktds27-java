package com.ktdsuniversity.edu.fp.basic.stream.object;

import java.util.Arrays;
import java.util.List;

public class DishList {

	public static List<Dish> makeDishList() {
		return Arrays.asList( //.asList 배열을 => 리스트로 바꿈 
			new Dish("돼지고기", FoodType.MEAT, 800, DishType.MEAT), 
			new Dish("소고기", FoodType.MEAT, 700, DishType.MEAT), 
			new Dish("치킨", FoodType.MEAT, 400, DishType.MEAT), 
			new Dish("프렌치 프라이", FoodType.VEGETABLES, 530, DishType.OTHER), 
			new Dish("쌀밥", FoodType.VEGETABLES, 350, DishType.OTHER), 
			new Dish("계절 과일", FoodType.VEGETABLES, 120, DishType.OTHER), 
			new Dish("피자", FoodType.VEGETABLES, 550, DishType.OTHER), 
			new Dish("새우", FoodType.MEAT, 300, DishType.FISH), 
			new Dish("연어", FoodType.MEAT, 450, DishType.FISH)
		);
	}
}
