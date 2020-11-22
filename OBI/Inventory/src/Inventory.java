import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Inventory {

	public static void main(String[] args) {
			
		Equipment[] items = new Equipment[4];
		
		items[0] = new Laptop("Dell", "Lattitude 7390", 5600.00, "czarny");
		items[1] = new Laptop("Apple", "MacBook Air 13", 7800.00, "srebrny, uszkodzona matryca");
		items[2] = new Laptop("Dell", "Lattitude 3940", 3200.00, "czerwony");
		items[3] = new Laptop("Dell", "Lattitude 3940", 3200.00, "czerwony");
				
		for ( Equipment i : items ) {
			System.out.println(i.getDescription()+"\n");
		}
		for ( Equipment i : items ) {
			System.out.println(i.toString());
		}
		
		//equals test
		System.out.print("\nCheck equals for Laptop class: items[2].equals(items[3]): ");
		System.out.println(items[2].equals(items[3]));
		
		items[2] = items[3];
		System.out.println("after //items[2] = items[3];");
		System.out.print("Check equals for Laptop class: items[2].equals(items[3]): ");
		System.out.println(items[2].equals(items[3]));
		
		//Array list test
		ArrayList<Furniture> furniture = new ArrayList<>();
		furniture.add(new Desk(120, 80, "sosnowe", 699.99));
		furniture.add(new Desk(110, 80, "dębowe", 1500.0));
		furniture.add(new Chair("Skórzane obicie", 341.00, true));
		
		System.out.println(furniture.size());
		
		
		for ( Equipment i : furniture ) {
			System.out.println(i.getDescription()+"\n");
		}
		for ( Equipment f : furniture ) {
			System.out.println(f.toString());
		}
		
		//comparable test
		System.out.println(furniture.get(0).compareTo(furniture.get(1)));
		
		//timer test
		var listener = new TimeDepreciation(items[0]);
		Timer t = new Timer(1000, listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	
	}
	
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
