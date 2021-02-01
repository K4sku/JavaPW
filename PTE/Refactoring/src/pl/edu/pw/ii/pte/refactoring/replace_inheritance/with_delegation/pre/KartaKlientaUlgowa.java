package pl.edu.pw.ii.pte.refactoring.replace_inheritance.with_delegation.pre;

public class KartaKlientaUlgowa 	extends KartaKlienta{
	
		public double naliczOplate(int dni) {
			return 0.4 * super.naliczOplate(dni);
		}

}
