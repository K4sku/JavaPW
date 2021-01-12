package propertyDemo1;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Bill {
	private DoubleProperty amountDue = new SimpleDoubleProperty();

	public double getAmountDue() {
		return amountDue.get();
	}

	public void setAmountDue(double value) {
		this.amountDue.setValue(value);
	}
	
	public DoubleProperty amountDueProperty() {
		return amountDue;
	}
}
