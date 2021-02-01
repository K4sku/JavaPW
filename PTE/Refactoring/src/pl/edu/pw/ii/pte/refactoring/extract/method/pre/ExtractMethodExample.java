package pl.edu.pw.ii.pte.refactoring.extract.method.pre;

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
		System.out.println(boss.firstName);
		System.out.println(boss.lastName);
		System.out.println("Employes:");
		for (Person employe : employes) {
			System.out.println(employe.firstName);
			System.out.println(employe.lastName);
		}
	}
}
