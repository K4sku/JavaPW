package model;

public class Person implements Comparable {
	private String firstName;
	private String lastName;
	private String roomNo;
	private int startHour;
	private int endHour;

	public Person(String firstName, String lastName, String roomNo, int startHour, int endHour) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNo = roomNo;
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public String getFirstName() { return firstName; }

	public void setFirstName(String s) {
		this.firstName = s;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String s) {
		this.lastName = s;
	}

	public int getEndHour() { return endHour; }

	public void setEndHour(int endHour) { this.endHour = endHour; }

	public String getRoomNo() { return roomNo; }

	public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

	public int getStartHour() { return startHour; }

	public void setStartHour(int startHour) { this.startHour = startHour; }

	public int getShiftLength() {
		if (this.getEndHour() <= this.getStartHour()) {
			return (24 - this.getStartHour() + this.getEndHour());
		} else {
			return (this.getEndHour() - this.getStartHour());
		}

	}

	@Override
	public int compareTo(Object o) {
		Person p = (Person) o;
		return Integer.compare(this.getShiftLength(), p.getShiftLength());
	}
}

