package com.ktdsuniversity.edu.fp.basic.advanced;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.ktdsuniversity.edu.fp.basic.advanced.utils.ArrayUtil;
import com.ktdsuniversity.edu.fp.basic.advanced.utils.StringUtil;

public class City {
	
	private int id;			  // 0
	private String name;         // 1
	private int stateId;         // 2 
	private String stateCode;    // 3
	private String stateName;     // 4
	private int countryId;       // 5
	private String countryCode;  // 6
	private String countryName;  // 7
	private String latitude;     // 8
	private String longitude;    // 9
	private String _native;      // 10
	private String type;         // 11
	private int level;           // 12
	private int parentId;        // 13
	private int population;      // 14
	private String timezone;     // 15
	private String wikiDataId;   // 16
	
	public static List<City> loadCityData() {
		String path = "C:\\Devprograms\\java-stream-countries-states-cities-database-master\\csv\\cities.csv";
		try {
			return Files.lines(new File(path).toPath()) // Stream<String> 지정된 경로(path)에 있는 파일 열어 한줄씩 읽어옴
					.skip(1) //Stream<String> 스트림의 첫번쨰 줄을 건너뜀, 파일의 첫줄
					.parallel() //병렬 처리 시작
					.map(City::new) //Stream<City>	읽어온 각 문자열(한줄)을 City객체 변환
									// 생성자 참조, 각 라인의 문자열을 인자로 받는 생성자가 호출 되어 새로운 인스턴스 생성
					.toList(); // List<City> //스트림에 쌓인 City 객체 모아서  최종적 List<City>형태로 반환
		}
		catch (IOException e) {
			return null;
		}
	}
	
	public City(String cityLineString) {
		//CSV : Comma Separate Value
		String[] values = cityLineString.split(",");
		
		this.id = StringUtil.toInt(ArrayUtil.getValue(values, 0));
		this.name = ArrayUtil.getValue(values, 1);
		this.stateId = StringUtil.toInt(ArrayUtil.getValue(values, 2));
		this.stateCode = ArrayUtil.getValue(values, 3);
		this.stateName = ArrayUtil.getValue(values, 4);
		this.countryId = StringUtil.toInt(ArrayUtil.getValue(values, 5));
		this.countryCode = ArrayUtil.getValue(values, 6);
		this.countryName = ArrayUtil.getValue(values, 7);
		this.latitude = ArrayUtil.getValue(values, 8);
		this.longitude = ArrayUtil.getValue(values, 9);
		this._native = ArrayUtil.getValue(values, 10);
		this.type = ArrayUtil.getValue(values, 11);
		this.level = StringUtil.toInt(ArrayUtil.getValue(values, 12));
		this.parentId = StringUtil.toInt(ArrayUtil.getValue(values, 13));
		this.population = StringUtil.toInt(ArrayUtil.getValue(values, 14));
		this.timezone = ArrayUtil.getValue(values, 15);
		this.wikiDataId = ArrayUtil.getValue(values, 16);
		
	}
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getStateId() {
		return this.stateId;
	}
	public String getStateCode() {
		return this.stateCode;
	}
	public String getStateName() {
		return this.stateName;
	}
	public int getCountryId() {
		return this.countryId;
	}
	public String getCountryCode() {
		return this.countryCode;
	}
	public String getCountryName() {
		return this.countryName;
	}
	public String getLatitude() {
		return this.latitude;
	}
	public String getLongitude() {
		return this.longitude;
	}
	public String get_native() {
		return this._native;
	}
	public String getType() {
		return this.type;
	}
	public int getLevel() {
		return this.level;
	}
	public int getParentId() {
		return this.parentId;
	}
	public int getPopulation() {
		return this.population;
	}
	public String getTimezone() {
		return this.timezone;
	}
	public String getWikiDataId() {
		return this.wikiDataId;
	}
	
	
	
}
