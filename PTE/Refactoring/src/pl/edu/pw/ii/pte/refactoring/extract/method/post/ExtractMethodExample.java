package pl.edu.pw.ii.pte.refactoring.extract.method.post;

import java.util.ArrayList;
import java.util.List;

public class ExtractMethodExample {

	public static class Person {
		public String firstName;
		public String lastName;
	}

	private Person boss = new Person();
	private List<Person> employes = new ArrayList<Person>();

	public void printPersosns() {
		System.out.println("Boss:");
		printPerson(boss);
		System.out.println("Employes:");
		for (Person employe : employes) {
			printPerson(employe);
		}
	}

	private void printPerson(Person p) {
		System.out.println(p.firstName);
		System.out.println(p.lastName);
	}
}
