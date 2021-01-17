package model;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	
	public Person(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String s) {
		this.firstName = s;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String s) {
		this.lastName = s;
	}
	
}

