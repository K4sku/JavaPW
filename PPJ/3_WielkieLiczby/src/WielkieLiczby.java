import java.util.Scanner;
import java.math.BigInteger;
import java.lang.Math; 

public class WielkieLiczby {

	public static void main(String[] args) {
		int n = odczytajN();
		//potega n^n
		BigInteger potegaN = BigInteger.valueOf((long) Math.pow(n,n));
		
		System.out.println("n^n");
		wypiszWartosc(potegaN);
		wypiszIloscCyfr(potegaN);
		
		//silnia n!
		BigInteger silniaN = BigInteger.valueOf(1L);
		for(int i=0; i < n ;i++) {
			silniaN = silniaN.multiply(BigInteger.valueOf((long) (i+1)));
		}
		
		System.out.println("n!");
		wypiszWartosc(silniaN);
		wypiszIloscCyfr(silniaN);
	}
	
	static int odczytajN() {
		Scanner input = new Scanner(System.in);
	    System.out.print("Podaj wartość n [1..99] \nn:");
	    int n = input.nextInt();
	    input.close();
	    return n;
	}
	
	static void wypiszIloscCyfr(BigInteger val) {
		int iloscCyfr = val.toString().length();
		System.out.println("Ilość cyfr: " + iloscCyfr);
	}
	
	static void wypiszWartosc(BigInteger val) {
		System.out.println("Wartość dec.: " + val.toString());
		System.out.println("Wartość hex.: " + val.toString(16));
	}	
}