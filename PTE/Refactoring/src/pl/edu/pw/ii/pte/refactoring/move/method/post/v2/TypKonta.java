package pl.edu.pw.ii.pte.refactoring.move.method.post.v2;

public class TypKonta {
	
	private boolean superKonto;
	
		
	public TypKonta (boolean typ) {
		superKonto = typ;
	}
	
	public boolean czySuperKonto() {
		return superKonto;
	}

	double oplataZaPrzekroczenieStanu (Konto konto) {
		if (czySuperKonto() ) {
			double wynik = 10;
			if (konto.getLiczbaDniPrzekroczenia() > 7) 		   
				wynik += (konto.getLiczbaDniPrzekroczenia() - 7 ) * 0.85;
			return wynik;
		   }
		  else 
		return konto.getLiczbaDniPrzekroczenia() * 1.75;
		}


	
	public void enableSuperKonto() {
		superKonto = true;
	}
	
	public void disableSuperKonto() {
		superKonto = false;
	}
}
