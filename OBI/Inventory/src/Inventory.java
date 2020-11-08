//import java.util.Objects;

public class Inventory {

	public static void main(String[] args) {
			
		Equipment[] items = new Equipment[3];
		
		items[0] = new Laptop("Dell", "Lattitude 7390", 5600.00, "czarny");
		items[1] = new Laptop("Apple", "MacBook Air 13", 7800.00, "srebrny, uszkodzona matryca");
		items[2] = new Laptop("Dell", "Lattitude 3940", 3200.00, "czerwony");
				
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
	}
	
	//TODO: create equals
}
