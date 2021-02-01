package pl.edu.pw.ii.pte.refactoring.move.method.pre;

public class Konto {
	
	TypKonta _typ;
	int _liczbaDniPrzekroczenia;
	
	public double oplataZaPrzekroczenieStanu() {
		if (_typ.czySuperKonto() ) {
			double wynik = 10;
			if (_liczbaDniPrzekroczenia > 7) 
				wynik += (_liczbaDniPrzekroczenia - 7 ) * 0.85;
			return wynik;
		   }
		else 
			 return _liczbaDniPrzekroczenia * 1.75;
	}
		
	public double oplataBankowa() {
	 	double wynik = 4.5;
		if (_liczbaDniPrzekroczenia > 0 )
			wynik += oplataZaPrzekroczenieStanu();
	 	return wynik;
	}
	
	public void set_liczbaDniPrzekroczenia(int liczba) {
		_liczbaDniPrzekroczenia = liczba;
	}

}
