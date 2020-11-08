
public abstract class Furniture extends Equipment {
	
	private String tag = "";
	private static int nextTag = 1;
	
	public abstract String getDescription();

	public Furniture(String notes, double value) {
		super(notes, value);
		setTag();
	}
	
	public String toString() {
		return super.toString() + 
				"<-Furniture[tag: " + getTag() + "]";
	}

	public String getTag() { return tag; }
	void setTag() {
		this.tag = "FUR."+nextTag;
	    nextTag++;
	}

}
