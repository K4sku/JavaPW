package pl.edu.pw.ii.pte.refactoring.move.method.post.v2;

public class Konto {
	
	private TypKonta _typ;
	private int _liczbaDniPrzekroczenia;
		
	public double oplataZaPrzekroczenieStanu() {
		 return _typ.oplataZaPrzekroczenieStanu(this);

	}
	
	public double oplataBankowa() {
	 	double wynik = 4.5;
		if (_liczbaDniPrzekroczenia > 0 )
			wynik += _typ.oplataZaPrzekroczenieStanu(this);

	 	return wynik;
	}
	
	public void setLiczbaDniPrzekroczenia(int liczba) {
		_liczbaDniPrzekroczenia = liczba;
	}
	
	public int getLiczbaDniPrzekroczenia() {
		return _liczbaDniPrzekroczenia;
	}

}
