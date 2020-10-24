// Klasy abstrakcyjne i interfejsy

public class PPJ3_FiguryInterfejsy {

  public static void main(String[] args) {
    
    //Figura ff = new Figura();		// Nie można tworzyć instancji
                                 	// klasy abstrakcyjnej Figura
    Figura hf = new Histogram();	hf.dokuj(1,1); hf.dekoruj(11);
    Figura wf = new Wielobok();		wf.dokuj(2,2); wf.dekoruj(22);
    Figura bf = new Bezier();			bf.dokuj(3,3); bf.dekoruj(33);
    Figura ef = new Elipsa();  		ef.dokuj(4,4); ef.dekoruj(44);
    Figura lf = new Lamana();  		lf.dokuj(5,5); lf.dekoruj(55);
    
    Figura[] figury = new Figura[]{ hf, bf, wf, hf, lf, ef, null };
    System.out.println("\nPrzed pokazem\n");
    rysuj(figury);
    System.out.println("\nPo pokazie\n");

   }	// main()
   
   static void rysuj(Figura[] figury) { 
     int i;
     for(i=0; figury[i] != null; ++i)
       figury[i].rysuj();

     System.out.println("Liczba figur: " + i);
   }
  
}	// PPJ3_FiguryInterfejsy

interface Dokowalny {
  void dokuj(int x, int y); // Implicite public abstract
  // ... inne metody 
}
interface Dekorowalny extends Dokowalny {
  void dekoruj(int wariant);
  // ...
}

//Figury geometryczne
abstract class Figura implements Dekorowalny { // Klasa abstrakcyjna
  abstract void rysuj();         // Metoda abstrakcyjna
  void transform(){ /* ... */ } // Metoda wirtualna "zwykła"
  
  public void dokuj(int x, int y) { this.x=x; this.y=y; }
  public void dekoruj(int wariant) { this.wariant = wariant; }
  protected String atrybuty() {
  	return "DockAt ("+x + ","+y+"), StyleIs "+wariant; 
  }
  
  private int x=0, y=0, wariant=0;
  // inne metody
}

class Wielobok extends Figura { 
	Wielobok()  { System.out.println("Powstał wielobok"); }
	void rysuj() { System.out.println("Wielobok: "+atrybuty()); }
}

class Histogram extends Figura { 
	Histogram() { System.out.println("Powstał histogram"); }
	void rysuj() { System.out.println("Histogram: "+atrybuty()); }
}

class Bezier extends Figura { 
	Bezier()    { System.out.println("Powstała krzywa Beziera"); } 
	void rysuj() { System.out.println("Bezier: "+atrybuty()); }
}

abstract class FiguraZamknieta extends Figura {}
abstract class FiguraOtwarta   extends Figura {}

class Lamana extends FiguraOtwarta {
	Lamana() { System.out.println("Powstała lamana"); }
	void rysuj() { System.out.println("Lamana: "+atrybuty()); }
}

class Elipsa extends FiguraZamknieta {
	Elipsa() { System.out.println("Powstała elipsa"); }
	void rysuj() { System.out.println("Elipsa: "+atrybuty()); }
}
