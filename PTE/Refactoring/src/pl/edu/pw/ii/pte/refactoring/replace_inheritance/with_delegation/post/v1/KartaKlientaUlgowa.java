package pl.edu.pw.ii.pte.refactoring.replace_inheritance.with_delegation.post.v1;


public class KartaKlientaUlgowa {
	
	private KartaKlienta karta;
	
	public KartaKlientaUlgowa(KartaKlienta karta) {	
		this.karta = karta;
	}

	public double naliczOplate(int dni) {
			return 0.4 * karta.naliczOplate(dni);
		}

		
	public Klient klient() {
			return karta.klient();
		}

}
