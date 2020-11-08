
public class Chair extends Furniture {

	public Chair(String notes, double value) {
		super(notes, value);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return super.toString() + 
				"<-Chair[" + "]";
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
