package pl.edu.pw.ii.pte.refactoring.move.method.post.v4;

//Eclipse Move without "keep original method as delegate"

public class Konto {
	
	TypKonta _typ;
	int _liczbaDniPrzekroczenia; 
		
	public double oplataBankowa() {
	 	double wynik = 4.5;
		if (_liczbaDniPrzekroczenia > 0 )
			wynik += _typ.oplataZaPrzekroczenieStanu(this);
	 	return wynik;
	}
	
	public void set_liczbaDniPrzekroczenia(int liczba) {
		_liczbaDniPrzekroczenia = liczba;
	}

}
