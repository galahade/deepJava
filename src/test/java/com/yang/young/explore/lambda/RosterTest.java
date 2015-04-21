package com.yang.young.explore.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {

	static interface CheckPerson {
		boolean test(Person P);
		
	}

	class CheckPersonEligibleForSelectiveService implements CheckPerson {
		public boolean test(Person p) {
			return p.gender == Person.Sex.MALE && p.getAge() >= 18
					&& p.getAge() <= 25;
		}

	}

	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for (Person p : roster) {
			if (p.getAge() >= age) {
				p.printPerson();
			}
		}
	}

	public static void printPersonsWithinAgeRange(List<Person> roster, int low,
			int high) {
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}

	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	public static void printPersonsWithPredicate(List<Person> roster,
			Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	public static void proccessPersons(List<Person> roster,
			Predicate<Person> tester, Consumer<Person> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}

	public static void proccessPersonWithFunction(List<Person> roster,
			Predicate<Person> tester, Function<Person, String> mapper,
			Consumer<String> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}

	}
	
	public static <X, Y> void processElements(
			Iterable<X> source,
			Predicate<X> tester,
			Function<X, Y> mapper,
			Consumer<Y> block
			) {
		for (X p : source) {
			if (tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}
}
