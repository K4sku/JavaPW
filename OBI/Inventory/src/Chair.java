public class Chair extends Furniture implements Comparable<Object> {
	
	private boolean onWheels;

	public Chair(String notes, double value, boolean onWheels) {
		super(notes, value);
		setOnWheels(onWheels);
	}

	public String toString() {
		return super.toString() + 
				" <-Chair[onWheels: " + isOnWheels() + "]";
	}
	
	@Override
	public String getDescription() {
		if (isOnWheels()) {
			return "Tag: " +getTag() + 
					"\nChair on wheels." +
					"\nAdd Date: " + getCreationDate() + 
					"\nNotes: " + getNotes();
		}
		return "Tag: " +getTag() + 
				"\nChair without wheels." +
				"\nAdd Date: " + getCreationDate() + 
				"\nNotes: " + getNotes();
	}

	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		
		Chair other = (Chair) otherObject;
		return onWheels == other.onWheels;
	}
	
	@Override
	public int compareTo(Object otherObject) {
		return 0;
	}
	
	public boolean isOnWheels() { return onWheels; }
	public void setOnWheels(boolean onWheels) {	this.onWheels = onWheels; }
	
}
