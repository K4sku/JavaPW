package pl.edu.pw.ii.pte.refactoring.replace_inheritance.with_delegation.post.v1tmp;

public class KartaKlienta {
	
	private Klient klient;
	
	public Klient klient() {
		return klient;
	}
	public double naliczOplate(int dni) {
		return 10 * dni;
	}
	
}
