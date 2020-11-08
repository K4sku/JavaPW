import java.time.LocalDate;

public abstract class Equipment {
	
	public abstract String getDescription();
	abstract void setTag();
	
	private static int nextId = 1;
	private int id;
	private LocalDate creationDate = LocalDate.now();
	private String notes = "";
	private double value;
		   
	public Equipment(String notes, double value) {
		setNotes(notes);
		setValue(value);
		setId();
	}
	
	public String toString() {
		return "Equipment[id: " + getId() + ", creationDate: " + getCreationDate() +", value: " + getValue() + ", notes: " + getNotes() + "]";
	}
	
	// getters and setters
	public String getNotes() { return notes;	}
	public void setNotes(String notes) { this.notes = notes; }
	
	public double getValue() { return value; }
	public void setValue(double value) { this.value = value; }
	
	public int getId() { return id; }
	void setId() {
		this.id = nextId;
	    nextId++;
	}
	
	public LocalDate getCreationDate() { return creationDate; }
	public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate;	}

}


