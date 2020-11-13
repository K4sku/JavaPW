//import java.util.Objects;

public class Inventory {

	public static void main(String[] args) {
			
		Equipment[] items = new Equipment[4];
		
		items[0] = new Laptop("Dell", "Lattitude 7390", 5600.00, "czarny");
		items[1] = new Laptop("Apple", "MacBook Air 13", 7800.00, "srebrny, uszkodzona matryca");
		items[2] = new Laptop("Dell", "Lattitude 3940", 3200.00, "czerwony");
		items[3] = new Laptop("Dell", "Lattitude 3940", 3200.00, "czerwony");
				
		for ( Equipment item : items ) {
			System.out.println(item.getDescription()+"\n");
		}
		
		for ( Equipment item : items ) {
			System.out.println(item.toString());
		}
		
		Desk biurko = new Desk(120, 80, "sosnowe", 699.99);
		System.out.println(biurko.toString());
		
		Chair krzeslo = new Chair("sosnowe", 341.00);
		System.out.println(krzeslo.toString());
		
		System.out.print("\nCheck equals for Laptop class: items[2].equals(items[3]): ");
		System.out.println(items[2].equals(items[3]));
		
		System.out.println("//items[2] = items[3];");
		items[2] = items[3];
		System.out.print("Check equals for Laptop class: items[2].equals(items[3]): ");
		System.out.println(items[2].equals(items[3]));
	}
	
	//TODO: create equals
}
