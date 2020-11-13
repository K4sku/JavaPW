public class Desk extends Furniture {
	
	private int length;
	private int width;

	public Desk(int length, int width, String notes, double value) {
		super(notes, value);
		setLength(length);
		setWidth(width);
	}

	public String toString() {
		return super.toString() + 
				"<-Desk[length: " + getLength() + ", width: " + getWidth() + "]";
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		
		Desk other = (Desk) otherObject;
		return length == other.length && width == other.width;
	}

	
	//getters and setters
	public double getLength() {	return length; }
	public void setLength(int length) { this.length = length; }
	
	public double getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
}
