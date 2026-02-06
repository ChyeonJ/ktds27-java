package com.ktdsuniversity.edu.fp.basic.stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ktdsuniversity.edu.fp.basic.stream.object.Dish;
import com.ktdsuniversity.edu.fp.basic.stream.object.DishList;
import com.ktdsuniversity.edu.fp.basic.stream.object.DishType;
import com.ktdsuniversity.edu.fp.basic.stream.object.FoodType;

public class StreamBasic {
	
	public void printDishUseFor() {
		System.out.println("전체 요리 목록 - 칼로리가 500 미만인 && FoodType MEAT인 && DishType이 FISH인 - for");
		List<Dish> dishList = DishList.makeDishList();
		for(Dish dish : dishList){
			if(dish.getCalories() < 500 && dish.getFoodType() == FoodType.MEAT 
					&& dish.getDishType() == DishType.FISH) {
				System.out.println(dish);
			}
		}
	}
	
	public void printDishUseStream() {
		
		//List에 들어있는 forEach를 사용 (잘 사용하지 않음)
		System.out.println("전체 요리 목록 - 칼로리가 500미만인 && FoodType MEAT인 && DishType이 FISH인 - list.forEach");
		List<Dish> dishList = DishList.makeDishList();
	
		
		//dishList.forEach(dish -> System.out.println(dish));
		//어제 배운 Method Reference를 사용한 모습 (잘 사용하지 않음)
//		dishList.forEach(System.out::println);
		
		dishList.forEach(dish -> {
			if (dish.getCalories() < 500&& dish.getFoodType() == FoodType.MEAT 
					&& dish.getDishType() == DishType.FISH) {
				System.out.println(dish);
			}
		});
		
		System.out.println("전체 요릭 - 목록 칼로리가 500미만인 && FoodType MEAT인 && DishType이 FISH인 - Stream");
		//Stream을 사용할 수 있는 대상 -> List,Set
		dishList	//List<Dish> <-Stream을 할 떄 뭐가 생기나, E를 준다 Stream을 호출한 인스턴스의 제네릭이다.
				.stream()
				.peek(dish ->{//현재 반복중인 인스턴스를 확인하는 용도
					System.out.println("첫번쨰 filter를 수행하기 이전의 인스턴스 값");
					System.out.println("1." + dish.getName());
					System.out.println("1." + dish.getCalories());
					System.out.println("1." + dish.getFoodType());
					System.out.println("1." + dish.getDishType());
				}) 
				.filter(dish -> dish.getCalories() < 500) //filter가 반복하지 않고, .stream이 반복한다.
														// Stream<boolean>이 갈 것이냐? 아니 Strim<Dish>로 T를 보낸다.
				.peek(dish ->{//현재 반복중인 인스턴스를 확인하는 용도
					System.out.println("두번쨰 filter를 수행하기 이전의 인스턴스 값");
					System.out.println("2." + dish.getName());
					System.out.println("2." + dish.getCalories());
					System.out.println("2." + dish.getFoodType());
					System.out.println("2." + dish.getDishType());
				}) 
				.filter(dish -> dish.getFoodType() == FoodType.MEAT)
				.peek(dish ->{//현재 반복중인 인스턴스를 확인하는 용도
					System.out.println("세번쨰 filter를 수행하기 이전의 인스턴스 값");
					System.out.println("3." + dish.getName());
					System.out.println("3." + dish.getCalories());
					System.out.println("3." + dish.getFoodType());
					System.out.println("3." + dish.getDishType());
				})
				.filter(dish -> dish.getDishType() == DishType.FISH)
				.peek(dish ->{//현재 반복중인 인스턴스를 확인하는 용도
					System.out.println("네번쨰 filter를 수행하기 이전의 인스턴스 값");
					System.out.println("4." + dish.getName());
					System.out.println("4." + dish.getCalories());
					System.out.println("4." + dish.getFoodType());
					System.out.println("4." + dish.getDishType());
				})
				.forEach(dish -> System.out.println(dish));
		
	}
	
	public void printEvenNumbers() {
		
		List<Integer> numbers = Arrays.asList(23,123,54,123,346,432,346,23,234,4356,23,45,346,234,123,3);
		
		// 1. numbers에 있는 값을 전부 2를 곱해서 짝수로 출력
		numbers.stream()
				.map(num -> num * 2)
				.forEach(num -> System.out.println(num));
		
		System.out.println("-".repeat(40));
		
		// 2. numbers에 있는 값에서 중복된 숫자는 모두 제거하고, 나머지 숫자에 전부 2를 곱해서 짝수로 만들어 출력한다
		numbers.stream()
				.distinct()	//중복된 숫자 제거
				.forEach(num -> System.out.println(num));
		
	}
	
	//Collector Collectors
	public String makeString() {
		
		//모든 VEGETABLES 메뉴의 이름들을 ","로 연결한 문자열을 반환한다.
		
		List<Dish> dishList = DishList.makeDishList();
		
		String dishsName = dishList.stream()	//Strem<Dish>
									.filter(dish -> dish.getFoodType() == FoodType.VEGETABLES)
									.map(Dish::getName) //Stream<String>
									.collect( Collectors.joining(", ")); //최종함수
		
		return dishsName;
	}
	
	//Collector toList -> 스트림의 결과로 수정 불가능한 리스트를 만들고 싶다 ~ toList()
	public List<Dish> getHealthyDishes() {
		// 변경 불가능한 리스트 (add불가)
		List<Dish> dishes = DishList.makeDishList();
		
		List<Dish> result = dishes.stream()
									.filter(dish -> dish.getCalories() < 400)
									.toList();
		
		//변경 불가능한 리스트
		return result;
	}
	//Collector toList -> 스트림의 결과로 수정 가능한 리스트를 만들고 싶다
	public List<Dish> getHealthyDishes2() {
		// 변경 불가능한 리스트 (add불가)
		List<Dish> dishes = DishList.makeDishList();
		
		List<Dish> result = dishes.stream()
				.filter(dish -> dish.getCalories() < 400)
				.collect(Collectors.toList());
		
		//변경 가능한 리스트
		return result;
	}
	
	public static void main(String[] args) {
		
		StreamBasic basic = new StreamBasic();
		basic.printDishUseFor();
		basic.printDishUseStream();
		
		basic.printEvenNumbers();
		String dishesName = basic.makeString();
		System.out.println(dishesName);
		
		List<Dish> result = basic.getHealthyDishes();
		System.out.println(result);
//		result.add(new Dish("곱창", FoodType.MEAT, 300, DishType.MEAT));
		
		List<Dish> result1 = basic.getHealthyDishes2();
		System.out.println(result1);
		result1.add(new Dish("곱창", FoodType.MEAT, 300, DishType.MEAT));
		System.out.println(result1);
		
	}
	
}
