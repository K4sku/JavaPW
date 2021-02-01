package model;

public class Person {
	private String id;
	private String firstName;
	private String lastName;
	
	public Person(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String i) {
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

