
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
		return "Tag: " +getTag() + "\n"
				+ "Laptop: " + getMake() + " " + getModel() + "\n"
						+ "Add Date: " + getCreationDate() + "\n"
								+ "Notes: " + getNotes();
    }
	
	public String toString() {
		return super.toString() + 
				"<-Laptop[tag: " + getTag() + ", make: " + getMake() + ", model: " + getModel() +"]";
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
