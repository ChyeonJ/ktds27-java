package com.ktdsuniversity.edu.app.v1;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
	
	private List<Contact> contactList;
	
	public ContactManager() {
		this.contactList = new ArrayList<Contact>();
	}
	
	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
	// 1. contactList에 Contant 인스턴스를 추가하는 기능
	public void addContact(Contact contact) {
		this.contactList.add(contact);
	}
	
	// 2. contactList의 모든 연락처 정보를 출력하는 기능
	public void printAllContacts() {
		
	}
	
	// 3. contactList에서 전화번호 검색결과 출력하는 기능
	// 예> 010을 파라미터로 전달하면 전화번호에 010이 포함된 연락처의 모든 정보를 출력
	public List<Contact> searchPhone(String number){
		return null;
	}
	
	// 4. contactList에서 이름 검색 결과 출력하는 기능
	// 예> "김"을 파라미터로 전달하면 name, firstName, lastName, companyName
	// 예> "김"이 포함된 연락처의 모든 정보를 출력
	public List<Contact> searchName(String name){
		return null;
	}
	
	// 5. contactList에서 이메일 검색결과 출력하는 기능
	public List<Contact> searchEmail(String email){
		
		return null;
	}
	
	// 6. 연락처 정보 수정 기능
	// 예> 다양한 검색의 결과 중 하나를 선택해 연락처 정보를 수정할 수 있는 기능.
	// 이름을 변경, 전화번호 추가, 전화번호 수정, 회사 정보 수정.....
	public void replaceContact(int index, Contact contact) {
		
	}
	
	// 7. 연락처 정보 삭제 기능
	public void deleteContact(int index) {
		
	}
	
	
	public static void main(String[] args) {
		
		ContactManager cm = new ContactManager();
		
		Contact contact = new Contact();
		contact.setName("최현종");
		contact.setEmail("asdasd@asd.com");
		contact.setNickName("별명");
		contact.setFirstName("현종");
		contact.setLastName("ㅇㅇㅇ");
		contact.setMemo("ㅇㅇㅇ");
		contact.getPhones().add(new Phone(Phone.Type.PERSONAL, "010-1234-5678"));
		contact.getPhones().add(new Phone(Phone.Type.HOME, "010-1234-5678"));
		contact.getPhones().add(new Phone(Phone.Type.COMPANY, "010-1234-5678"));
		contact.setCompany(new Company("KT", "사원", "경기"));
		
		
		
		
	}

}
