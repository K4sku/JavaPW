package propertyDemo1;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bill electricBill = new Bill();
		
		electricBill.amountDueProperty().addListener(
				(observable_value, old_value, new_value)->{
					System.out.println("Rachunek zmienony z "+ 
										old_value.toString()+
										" na: "+
										new_value.toString());
				});
		
		electricBill.setAmountDue(100.0);
		electricBill.setAmountDue(50.0);
		electricBill.setAmountDue(1000.0);
		//==========================
		IntegerProperty num1 = new SimpleIntegerProperty(1);
		IntegerProperty num2 = new SimpleIntegerProperty(10);
		IntegerProperty num3 = new SimpleIntegerProperty(15);
		
		NumberBinding sum = num1.add(num2);
		NumberBinding total = Bindings.add(num1, num2.multiply(num3));
		
		System.out.println("sum przed zmianą: " + sum.getValue());
		System.out.println("total przed zmianą: " + total.getValue());
		
		num2.set(1000);
		
		System.out.println("sum po zmianą: " + sum.getValue());
		System.out.println("total po zmianą: " + total.getValue());
		
		//=================
		Bill bill1 = new Bill();
		Bill bill2 = new Bill();
		Bill bill3 = new Bill();
		
		NumberBinding totalBill = Bindings.add(
								bill1.amountDueProperty(),
								bill2.amountDueProperty().add(bill3.amountDueProperty()));
		totalBill.addListener(observable->{
			System.out.println("Wiązanie nieważne.");
		});
		System.out.println("totalBill: " + totalBill.getValue());
		bill1.setAmountDue(300.0); // <= invalidation event
		bill2.setAmountDue(5000.0);
		bill3.setAmountDue(10.0);
		System.out.println("totalBill: " + totalBill.getValue());
		bill2.setAmountDue(11000.0); // <= invalidation event
		System.out.println("totalBill: " + totalBill.getValue());
		
		//=================
		Bill bill4 = new Bill();
		Bill bill5 = new Bill();
		Bill bill6 = new Bill();
		
		NumberBinding totalBill2 = Bindings.add(
								bill4.amountDueProperty(),
								bill5.amountDueProperty().add(bill6.amountDueProperty()));
		totalBill2.addListener((observable, old_val, new_val)->{
			System.out.println("Wiązanie zmienione.");
		});
		System.out.println("totalBill2: " + totalBill2.getValue());
		bill4.setAmountDue(300.1); // <= change event
		bill5.setAmountDue(5000.2); // <= change event
		bill6.setAmountDue(10.3); // <= change event
		System.out.println("totalBill2: " + totalBill2.getValue());
		bill5.setAmountDue(11000.0);  // <= change event
		System.out.println("totalBill2: " + totalBill2.getValue());
	}


}
