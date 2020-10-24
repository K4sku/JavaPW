import java.util.Scanner;

public class PPJ4_BryłyPlatońskie {
	
	public static void main(String[] args) {
		
		System.out.println(
				"Prezentacja wykorzystania typu wyliczeniowego\n" +
				"RegularSolid opisującego 5 brył platońskich.\n" +
				"Pogram oblica powierzchnię bryły dla podanej krawędzi.");
		
		try (Scanner scn = new Scanner(System.in)) {
			double a;
			while(true) {
  			System.out.print("\nKrawędź bryły: a = ");
  			a = scn.nextDouble();
  			if(a==0.0) break;

  			for(RegularSolid rs: RegularSolid.values()) 
  				System.out.println(rs + ": surface("+a+") = "+rs.surface(a));
  			System.out.println();
  			
  			for(RegularSolid rs: RegularSolid.values()) 
  				System.out.printf("%9s: surface(%.2f) = %.4f\n", rs, a, rs.surface(a));
			}
		}
		System.out.println("\nKoniec programu");
	}	// main()

}	// PPJ4_BryłyPlatońskie

// Typ wyliczeniowy dla brył platońskich
enum RegularSolid {
	TETRAEDR ( 4,  6,  4),	// Czworościan
	HEKSAEDR ( 8, 12,  6),	// Sześcian
	OKTAEDR  ( 6, 12,  8),	// Ośmiościan
	DODEKAEDR(20, 30, 12),	// Dwunastościan
	IKOSAEDR (12, 30, 20);	// Dwudziestościan

	// Liczba wierzchołków, krawędzi, ścian (vertex, edge, face)
	private final int v, e, f;
	private double area;	// Powierzcznia bryły dla krawędzi a=1
	
	// Prywatny konstruktor
	private RegularSolid(int v, int e, int f) {
		this.v = v;
		this.e = e;
		this.f = f;
		switch(f) {
  		case 4:	area = Math.sqrt(3); break;
  		case 6:	area = 6.0; break;
  		case 8:	area = 2*Math.sqrt(3); break;
  		case 12:area = 3*(Math.sqrt(25+10*Math.sqrt(5))); break;
  		case 20:area = 5*Math.sqrt(3); break;
		}		
	}
	// Przykładowe usługi publiczne
  public int vertexNumber(){ return v; }
  public int edgeNumber()  { return e; }
  public int faceNumber()  { return f; }
  public double surface(double a)  { return area*a*a; }
}
