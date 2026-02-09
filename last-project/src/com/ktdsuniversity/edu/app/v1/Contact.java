package com.ktdsuniversity.edu.app.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ktdsuniversity.edu.app.v1.Exception.MissMatchTypeException;
import com.ktdsuniversity.edu.app.v1.Exception.PhoneNullException;
import com.ktdsuniversity.edu.app.v1.Exception.ResultNullException;

public class Contact {
	
	/**
	 * 연락처의 이름
	 */
	private String name;
	/**
	 * 연락처 별명
	 */
	private String nickName;
	/**
	 * 연락처 이메일
	 */
	private String email;
	/**
	 * 현종(이름)
	 */
	private String firstName;
	/**
	 * 최(성)
	 */
	private String lastName;
	/**
	 * 전화번호 목록 너만 리스트냐 왜
	 */
	private List<Phone> phones;
	/**
	 * 근무중인 회사 정보
	 */
	private Company company;
	/**
	 * 메모
	 */
	private String memo;
	
	public Contact() {
		this.phones = new ArrayList<Phone>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "이름 : " + this.name + " 별명 : " + this.nickName + " 이메일 : " + this.email +
				" 중간 이름 : " + this.firstName + " 성씨 : " + this.lastName + this.phones + " " + this.company;
	}
	
	//예외처리 null 체크 후 객체 검사
	public boolean addPhoneTypeCheck(String type, String pNumber) {
		
		if(type != null && pNumber != null) {
			for(Phone.Type t : Phone.Type.values()) {
				if(t.name().equalsIgnoreCase(type) && pNumber.length() == 11) {
					return true;
				}
			}
			throw new MissMatchTypeException("타입이 일치 하지 않거나, 휴대폰 번호를 잘못 입력했습니다.");
		}else {
			throw new PhoneNullException("값이 비어 있습니다.");
		}
	}
	
	public Phone addPhone() {
		Scanner sc = new Scanner(System.in);
		System.out.print("분류 코드 입력 : ");
		String type = sc.next();
		System.out.print("\n휴대폰 번호 입력 : ");
		String pNumber = sc.next();
		
		
		if(addPhoneTypeCheck(type, pNumber)) {
			Phone.Type pType = Phone.Type.valueOf(type.toUpperCase());
			Phone newPhone = new Phone(pType, pNumber);
			this.phones.add(newPhone);
			System.out.println("\n번호가 등록 되었습니다." );
			System.out.println(newPhone.toString());
			return newPhone;
		}
		
		return null;
	}
	
	public String chekJob(String job) {
		
		if(job != null) {
			if(job.equals("사원") || job.equals("대리")|| job.equals("과장")
					|| job.equals("차장")|| job.equals("부장")|| job.equals("이사")
					|| job.equals("대표")) {
				return job;
			}
		}
		throw new ResultNullException("값을 제대로 넣어주세요");
	}
		
	
	public Company addCompany(Phone phone) {
		Company company = null;
		Scanner sc = new Scanner(System.in);
		if(phone.getPhoneType() == Phone.Type.COMPANY) {
			System.out.print("\n" +"근무중인 회사 이름 : ");
			String cName = checkNull(sc.next());
			
			System.out.print("\n" +"직급 : ");
			String cJob = chekJob(sc.next());
			
			System.out.print("\n" +"근무중인 회사 주소 : ");
			String cAddress = checkNull(sc.next());
			
			company = new Company(cName, cJob, cAddress);
			
			return company;
			
		}
		return null;
	}
	
	public Contact addContactListReady() {
		Contact newContact = new Contact();
		
		Scanner sc = new Scanner(System.in);
		
		//이름
		System.out.print("이름 : ");
		newContact.setName(checkNull(sc.next()));
		//연락처 별명
		System.out.print("\n별명 : ");
		newContact.setNickName(checkNull(sc.next()));
		//연락처 이메일
		System.out.print("\n이메일 : ");
		newContact.setEmail(checkNull(sc.next()));
		//이름
		System.out.print("\n중간 이름 : ");
		newContact.setFirstName(checkNull(sc.next()));
		//성씨
		System.out.println("\n성씨 : ");
		newContact.setLastName(checkNull(sc.next()));
		//전화번호 목록
		Phone phone = newContact.addPhone();
		System.out.println(phone.getPhoneType());
		//근무중인 회사 정보
		Company company = newContact.addCompany(phone);
		newContact.setCompany(company);
		//메모
		System.out.print("\n메모 : ");
		newContact.setMemo(sc.next());
		
		return newContact;
	}
	
	public String checkNull(String result) {
		
		if(result != null) {
			return result;
		}
		throw new ResultNullException("값을 제대로 넣어주세요");
	}
	
	
}
