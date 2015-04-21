package com.yang.young.explore.lambda;

import static com.yang.young.explore.lambda.Person.Sex.MALE;
import static com.yang.young.explore.lambda.RosterTest.printPersons;
import static com.yang.young.explore.lambda.RosterTest.printPersonsWithPredicate;
import static com.yang.young.explore.lambda.RosterTest.proccessPersonWithFunction;
import static com.yang.young.explore.lambda.RosterTest.proccessPersons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestRosterTest {
	
	private List<Person> roster;
	
	
	@Before
	public void setup() {
		roster = new ArrayList<Person>();
		
		Person p1 = new Person();
		p1.setName("Test1");
		p1.setAge(26);
		p1.setGender(MALE);
		p1.setBirthday(LocalDate.ofYearDay(1985, 25));
		Person p2 = new Person();
		p2.setName("Test2");
		p2.setAge(25);
		p2.setGender(MALE);
		p2.setBirthday(LocalDate.ofYearDay(1981, 25));
		
		roster.add(p1);
		roster.add(p2);
	}

	
	@Test
	public void testStandardLambda() {
		printPersons(roster, p -> p.getGender() == MALE );
		
		printPersonsWithPredicate(roster, (Person p) -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
		printPersonsWithPredicate(roster, p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
		proccessPersons(roster, 
				p -> p.gender == Person.Sex.MALE 
					&& p.getAge() >= 18 
					&& p.getAge() <= 25, 
				p -> p.printPerson());
		proccessPersonWithFunction(roster,
				p -> p.gender == Person.Sex.MALE 
				&& p.getAge() >= 18 
				&& p.getAge() <= 25,
				p -> p.getName(), 
				name -> System.out.println(name));
	}
	
	@Test
	public void test1() {
		RosterTest.processElements(
				roster, 
				p -> p.getGender() == MALE && p.getAge() >= 18,
				p -> p.getName(), 
				name -> System.out.println(name));
	}
	
	@Test 
	public void test2() {
		roster
			.stream()
			.filter(
				p -> p.getGender() == MALE && p.getAge() >= 18 && p.getAge() <=25)
			.map(p -> p.getName())
			.forEach(name -> System.out.println(name));
	}
	
	@Test
	public void testCompareAge() {
		Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);
		Arrays.sort(rosterAsArray,Person::compareByAge);
		Person p = rosterAsArray[0];
		Assert.assertEquals(25, p.getAge());
	}
	
	@Test
	public void test_reference_instance_method_of_arbitrary_object() {
		String[] stringArray = { "James", "Mary", "John",
			    "Patricia", "Robert", "Michael", "Linda","Barbara" };
			Arrays.sort(stringArray, String::compareToIgnoreCase);
			
			Assert.assertEquals("Barbara", stringArray[0]);
	}
	
	@Test
	public void test_constructor() {
		Set<Person> rosterSetLambda = transferElements(roster, HashSet<Person>::new);
		Assert.assertEquals(2, rosterSetLambda.size());
	}
	
	public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
		DEST transferElements (SOURCE sourceCollection, Supplier<DEST> collectionFaction) {
		
		DEST result = collectionFaction.get();
		for(T t : sourceCollection) {
			result.add(t);
		}
		return result;
	}
}
