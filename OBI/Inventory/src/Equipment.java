import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Equipment {
	
	public abstract String getDescription();
	abstract void setTag();
	abstract String getTag();
	
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
	
	public boolean equals(Object otherObject) {
		if (null == otherObject) return false;
		if (this == otherObject) return true;
		if (getClass() != otherObject.getClass()) return false;
		Equipment other = (Equipment) otherObject;
		
		return id == other.id && Objects.equals(creationDate, other.creationDate) && Objects.equals(notes, other.notes) && value == other.value;
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

	
	
	static class TimeDepreciation implements ActionListener {
		
		private Equipment anItem;
		
		public TimeDepreciation(Equipment equipment) {
			anItem = equipment;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			devaluateOverTime(anItem);
			System.out.println("Some time has passed. " + anItem.getTag() + " lost value, and now is only worth: " + anItem.getValue());
			
		}
		
		private void devaluateOverTime(Equipment anItem) {
			if (anItem.getValue() > 0) 
				anItem.setValue(Math.round(anItem.getValue()*0.95 * 100.0) / 100.0);
		}

	}
	
}


