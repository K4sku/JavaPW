package pl.edu.pw.ii.pte.refactoring.move.method.post.v1;

public class Konto {
	
	private TypKonta _typ;
	private int _liczbaDniPrzekroczenia;
		
	public double oplataZaPrzekroczenieStanu() {
		 return _typ.oplataZaPrzekroczenieStanu(
					_liczbaDniPrzekroczenia);

	}
	
	public double oplataBankowa() {
	 	double wynik = 4.5;
		if (_liczbaDniPrzekroczenia > 0 )
			wynik += _typ.oplataZaPrzekroczenieStanu(
					_liczbaDniPrzekroczenia);

	 	return wynik;
	}
	
	public void set_liczbaDniPrzekroczenia(int liczba) {
		_liczbaDniPrzekroczenia = liczba;
	}

}
