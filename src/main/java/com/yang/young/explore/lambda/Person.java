package com.yang.young.explore.lambda;

import java.time.LocalDate;

public class Person {
	
	public enum Sex {
		MALE,FEMALE
	}
	
	public Sex gender;
	
	public LocalDate birthday;
	
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Sex getGender() {
		return gender;
	}

	public void setGender(Sex gender) {
		this.gender = gender;
	}

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void  printPerson() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return "name:"+name+";age:"+age;
	}
	
	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}
	

}
