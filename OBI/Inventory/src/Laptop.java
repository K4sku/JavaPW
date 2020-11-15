import java.util.Objects;

public class Laptop extends Equipment{
	private String make = "";
	private String model = "";
	private String tag = "";
	private static int nextTag = 1;
	
	public Laptop(String make, String model, double value, String notes) {
		super(notes, value);
		setMake(make);
		setModel(model);
		setTag();
	}
	
	public String getDescription() {
		return "Tag: " +getTag() + 
				"\nLaptop: " + getMake() + " " + getModel() + 
				"\nAdd Date: " + getCreationDate() + 
				"\nNotes: " + getNotes();
    }
	
	public String toString() {
		return super.toString() + 
				" <-Laptop[tag: " + getTag() + ", make: " + getMake() + ", model: " + getModel() +"]";
	}
	
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		
		Laptop other = (Laptop) otherObject;
		return Objects.equals(make, other.make) && Objects.equals(model, other.model)  && Objects.equals(tag, other.tag);
	}

	public String getMake() { return make; }
	public void setMake(String make) { this.make = make; }
	
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }

	public String getTag() { return tag; }
	void setTag() {
		this.tag = "LAP."+nextTag;
	    nextTag++;
	}
}
//TODO comparable 