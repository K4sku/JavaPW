// Program demonstruje działanie mechanizmu obsługi 
// wyjątków i nielokalnego przekazywania sterowania.

public class PPJ4_SterowanieNielokalne {
  // Zmienna w decyduje czy będzie wyjątek: w==0 ==> OK
	static int w = 1;	
	public static void main(String[] args) {
		System.out.println("Aktywacja main()\n");
		try {	// Punkt kompetencji
			f();
		}
		catch(Exception e) {
			System.err.println("Wyjątek przechwycony: " + e);
			e.printStackTrace();
		}

		System.out.println("\nKoniec main()");
	}

	public static void f() throws Exception {
		System.out.println("Aktywacja f()");
		System.out.println("  fffffff");
		// ...
		g();
		System.out.println("Koniec f()");
	}

	public static void g() throws Exception {
		System.out.println("Aktywacja g()");
		System.out.println("  ggggggg");
		// ...
		h();
		System.out.println("Koniec g()");
	}
	
	public static void h() throws Exception {
		System.out.println("Aktywacja h()");
		System.out.println("  hhhhhhh");

		// ...
		if(w != 0) throw new Exception("w != 0");
		System.out.println("Koniec h()");
	}

}
