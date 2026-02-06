package com.ktdsuniversity.edu.fp.basic.stream.object;

public class Dish {
	
	private final String name;
	private final FoodType foodType;
	private final int calories;
	private final DishType dishType;

	public Dish(String name, FoodType foodType, int calories, DishType type) {
		this.name = name;
		this.foodType = foodType;
		this.calories = calories;
		this.dishType = type;
	}

	public String getName() {
		return this.name;
	}

	public FoodType getFoodType() {
		return this.foodType;
	}

	public int getCalories() {
		return this.calories;
	}

	public DishType getDishType() {
		return this.dishType;
	}

	@Override
	public String toString() {
		return this.name;
	}

}