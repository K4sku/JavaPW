package pl.edu.pw.ii.pte.refactoring.move.method.post.v3;

//Eclipse Move with "keep original method as delegate"

public class Konto {
	
	private TypKonta _typ;
	int _liczbaDniPrzekroczenia; //private delated
		
	/**
	 * @deprecated Use {@link pl.edu.pw.ii.pte.refactoring.move.method.post.v3.TypKonta#oplataZaPrzekroczenieStanu(pl.edu.pw.ii.pte.refactoring.move.method.post.v3.Konto)} instead
	 */
	public double oplataZaPrzekroczenieStanu() {
		return _typ.oplataZaPrzekroczenieStanu(this);
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
