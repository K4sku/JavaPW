package pl.edu.pw.ii.pte.refactoring.move.method.post.v1;

public class TypKonta {
	
	private boolean superKonto;
	
		
	public TypKonta (boolean typ) {
		superKonto = typ;
	}
	
	public boolean czySuperKonto() {
		return superKonto;
	}

	double oplataZaPrzekroczenieStanu (int liczba) {
		if (czySuperKonto() ) {
			double wynik = 10;
			if (liczba > 7) 
				wynik +=	(liczba - 7 ) * 0.85;
			return wynik;
		   }
		else return liczba * 1.75;
		}

	
	public void enableSuperKonto() {
		superKonto = true;
	}
	
	public void disableSuperKonto() {
		superKonto = false;
	}
}
