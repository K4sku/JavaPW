package pl.edu.pw.ii.pte.refactoring.move.method.post.v4;

public class TypKonta {
	
	private boolean superKonto;
	
	public TypKonta (boolean typ) {
		superKonto = typ;
	}
	
	public boolean czySuperKonto() {
		return superKonto;
	}

	public void enableSuperKonto() {
		superKonto = true;
	}
	
	public void disableSuperKonto() {
		superKonto = false;
	}

	public double oplataZaPrzekroczenieStanu(Konto konto) {
		if (czySuperKonto() ) {
			double wynik = 10;
			if (konto._liczbaDniPrzekroczenia > 7) 
				wynik += (konto._liczbaDniPrzekroczenia - 7 ) * 0.85;
			return wynik;
		   }
		else 
			 return konto._liczbaDniPrzekroczenia * 1.75;
	}
}
