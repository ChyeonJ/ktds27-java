package com.ktdsuniversity.edu.fp.basic.advanced;

import java.util.List;
import java.util.Optional;

public class CityTest {
	
	public static void main(String[] args) {
		List<City> cities = City.loadCityData();
		cities.forEach(System.out::println);
		
		// stateId가 3907번인 City의 CountryName을 출력
		for(City city : cities) {
			if(city.getStateId() == 3907) {
				System.out.println(city.getCountryName());
			}
		}
		
		System.out.println("=".repeat(40));
		// ==> Stream Code로 구현
		// stateId가 3907번인 City의 CountryName을 출력
		cities
				.stream()
				.filter(city -> city.getStateId() == 3907)
				.map(city -> city.getCountryName()) // T의 값을 보내고 반환 시키는 값의 타입이 R이 된다 City::getCountryName
				//.forEach(city -> System.out.println("1번문제 " + city.getCountryName()));
				.distinct()
				.forEach(System.out::println); // conturyName -> syso(countryName); 가능
				
		//countryNmae 이 "South Korea"인 City의 _native를 출력한
		cities
				.stream()
				.filter(city -> city.getCountryName().equals("South Korea"))
				.forEach(city -> System.out.println("2번문제 " + city.get_native()));
	
		//countryNmae 이 "South Korea"이면서 _native의 길이가 3이상인 City의 _native를 출력
		cities
				.stream()
				.filter(city -> city.getCountryName().equals("South Korea"))
				.filter(city -> city.get_native().length() >= 3)
				.forEach(city -> System.out.println("3번문제 " + city.get_native()));
		
		System.out.println("=".repeat(40));
		
		cities
		.stream()
		.filter(city -> city.getCountryName().equals("South Korea"))
		.filter(city -> city.get_native().length() >= 3)
		.skip(3) //동래구, 강서구, 금정구를 패스하겠다
		.limit(3) //기장군, 해운대구, 사하구 까지만 나온다
		.forEach(city -> System.out.println("skip, limit" + city.get_native()));
		
		/*
		 * 3번문제 동래구
		 *3번문제 강서구
		 *3번문제 금정구
		 *3번문제 기장군
		 *3번문제 해운대구
		 *3번문제 사하구
		 *3번문제 사상구
		 *3번문제 수영구
		 *3번문제 영도구
		 *3번문제 달서구
		 *3번문제 달성군
		 *3번문제 수성구
		 *3번문제 신탄신
		 *3번문제 송강동
		 *3번문제 유성구
		 *3번문제 철원군
		 *3번문제 춘천시
		 *3번문제 동해시
		 *3번문제 강릉시
		 *3번문제 고성군
		 *3번문제 횡성군
		 *3번문제 홍천군
		 *3번문제 화천군
		 */
		
		//_native의 값이 한글로만 이루어진 값 중에서 _native의 길이가 4글자 이상인 것의 name을 중복없이 조회한다.
		cities.stream()	//Stream<City>
				.filter(city -> city.get_native().matches("^[가-힣]{3,}$")) //Stream<City>
				.map(city -> city.getCountryName())//Stream<String>
				.distinct()//Stream<String>
				.map(String::length)
				.filter(length -> length >= 10)
				.distinct()
				.forEach(System.out::println);
		
		//애월읍의 stateName을 출력한다. => Find & Matching	=> 값이 있는거
		Optional<City> found = cities.stream()	//Stream<City>
				.filter(city -> city.getName().equals("Gaigeturi")) //Stream<City>
				.findFirst(); //Optional<City>
		System.out.println(found.orElse(null).getStateName()); //Optional POOOOINTOOOOOO
		
		//애월읍의 stateName을 출력한다. => Find & Matching	=> 값이 없는거
		Optional<City> found2 = cities.stream()	//Stream<City>
				.filter(city -> city.getName().equals("asd123asd")) //Stream<City>
				.findFirst(); //Optional<City>
		//System.out.println(found2.orElse(null).getStateName()); //error 발생!
		
		City city = found2.orElse(null);
		
		//Optional을 사용하는 케이스 1
		if(city != null) {
			System.out.println(city.getStateName());
		}
				
		//Optional을 사용하는 케이스 2
		if(found2.isPresent()) { //무조건 값이 있다면
			System.out.println(found2.get().getStateName());
		}
	
		//Optional을 사용하는 케이스 3
		City city2 = found2.orElse(new City(""));
		System.out.println(city2.getStateName());
		
		
		//최종함수를 사용하지 않은 Stream
		cities.stream()
				.peek(_city -> System.out.println(_city.get_native()))
				
				.filter(_city -> true)
				.map(_city -> _city.get_native());
	}

}
