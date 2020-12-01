public class Desk extends Furniture implements Comparable<Object> {
	
	private int length;
	private int width;

	public Desk(int length, int width, String notes, double value) {
		super(notes, value);
		setLength(length);
		setWidth(width);
	}

	public String toString() {
		return super.toString() + 
				" <-Desk[length: " + getLength() + ", width: " + getWidth() + "]";
	}
	
	@Override
	public String getDescription() {
		return "Tag: " +getTag() + 
				"\nDesk size (L x W): " + getLength() + " x " + getWidth() + 
				"\nAdd Date: " + getCreationDate() + 
				"\nNotes: " + getNotes();
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		
		Desk other = (Desk) otherObject;
		return length == other.length && width == other.width;
	}
	
	@Override
	public int compareTo(Object otherObject) {
		Desk other = (Desk) otherObject;
		return Integer.compare(length, other.length);
	}
	
	@Override
	public int compareTo() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//getters and setters
	public double getLength() {	return length; }
	public void setLength(int length) { this.length = length; }
	
	public double getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }


}
