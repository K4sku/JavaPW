package pl.edu.pw.ii.pte.patterns.adapter;

public class AdapterDemo {

	public static void main(String[] args) {
		Adaptee adaptee = new Adaptee();
		Adapter adapter = new Adapter(adaptee);
		
		adapter.clientRequest();

	}

}
