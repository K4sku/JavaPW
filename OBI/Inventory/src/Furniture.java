import java.util.Objects;

public abstract class Furniture extends Equipment implements Comparable<Object>{
	
	private String tag = "";
	private static int nextTag = 1;
	
	public abstract String getDescription();
	public abstract int compareTo();

	public Furniture(String notes, double value) {
		super(notes, value);
		setTag();
	}
	
	public String toString() {
		return super.toString() + 
				" <-Furniture[tag: " + getTag() + "]";
	}
	
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		
		Furniture other = (Furniture) otherObject;
		return Objects.equals(tag, other.tag);
	}

	public String getTag() { return tag; }
	void setTag() {
		this.tag = "FUR."+nextTag;
	    nextTag++;
	}

}
