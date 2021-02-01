package pl.edu.pw.ii.pte.refactoring.replace_inheritance.with_delegation.post.v1tmp;


public class KartaKlientaUlgowa 	extends KartaKlienta{
	
	private KartaKlienta karta = this;
	
		public double naliczOplate(int dni) {
			return 0.4 * karta.naliczOplate(dni);
		}

}
