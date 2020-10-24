// Testowanie operacji dwuargumentowych dla argumentów int
// Konwersacja tekstowa w strumieniach System.in, System.out.
import java.util.Scanner;

public class PPJ1_Operacje_IntOpInt {
	
  public static void main(String[] arg) {
  	// Komunikat wstępny o "misji" programu.
  	System.out.println(
  			"Program oblicza wartości generowane przez operatory\n" +
  			"dwuargumentowe dla argumentów typu int. Po znaku zachęty\n" +
  			"'>' należy wpisać wyrażenie postaci: arg1 op arg2\n" +
  			"op należy do zbioru: { *, /, %, +, -, &, |, ^ }.\n" +
  			"Koniec programu: Ctr^Z po znaku zachęty (Windows).\n"
  	);
  	
  	Scanner scn = new Scanner(System.in);   
    String op;
    int arg1, arg2, wynik=0;
		boolean koniec;	// Wykrywanie końca konwersacji

		while(true)	{ // Pętla konwersacji
  		koniec = false; op = "";

  		// Pobierz następne wyrażenie
  		System.out.print("> ");	// Zachęta w nowym wierszu
  		if(!scn.hasNext()) break;
  		arg1 = scn.nextInt();
  		op   = scn.next();
  		arg2 = scn.nextInt();
  		scn.nextLine();	// Pobierz i ignoruj resztę wiersza
	
	    // Obliczanie wyniku
	    switch(op) {
    		case "*": wynik = arg1 * arg2; break;
    		case "/": wynik = arg1 / arg2; break;
    		case "%": wynik = arg1 % arg2; break;
	    	case "+": wynik = arg1 + arg2; break;
	    	case "-": wynik = arg1 - arg2; break;
	    	case "&": wynik = arg1 & arg2; break;
	    	case "^": wynik = arg1 ^ arg2; break;
	    	case "|": wynik = arg1 | arg2; break;
	    	default:  koniec = true;
	    }
	    
      if(koniec) break;      
      System.out.printf("> %d %s %d = %d\n", arg1, op, arg2, wynik);
  	}
		scn.close();
    if(koniec) System.out.println("Nieznany operator "+op);
    System.out.println("\n&: " + Character.getName('&'));
    System.out.println("$: " + Character.getName('$'));
    System.out.println("@: " + Character.getName('@'));
    System.out.println("Ą: " + Character.getName('Ą'));
    System.out.println("Á: " + Character.getName('Á'));
    System.out.println("\nKoniec programu");
  }
}
