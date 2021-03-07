import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Std2_1_SzukanieBinarne {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rnum = new Random();
 
    System.out.println("Wyszukiwanie binarne w tablicy uporządkowanej."); 
    
    // Pętla konwersacji
    while(true ){
      int n, z;
      int tab[];

      System.out.print("\nRozmiar tablicy: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      tab = new int[n];
      System.out.print("Generować dane (t/n): ");
      String decyzja = scn.next();

      if(decyzja.equals("t")) {
        System.out.print("Jaki zakres generacji -z..z: z = ");
        z = scn.nextInt();
        
        for(int i=0; i<n; ++i){
          int r = rnum.nextInt(2*z+1) -z;
          System.out.print(r + "  ");
          tab[i] = r;
        }
        System.out.println();
      }
      else { // Wprowadzanie ręczne
        System.out.println("Liczby z klawiatury oddzielone spacjami (" + n + ")");
        for(int i=0; i<n; ++i)
          tab[i] = scn.nextInt();
      }
      
      // Szukanie binarne
      System.out.println("\nWyszukiwanie binarne w tablicy uporządkowanej tab.");
      System.out.println("Szukanie wartości x = 999 kończy wyszukiwanie.");
      Arrays.sort(tab);
      System.out.println("tab: "+ Arrays.toString(tab));
      while(true) {
        System.out.print("\nWyszukaj x = ");
        int x = scn.nextInt();
        if(x == 999) break;  
        System.out.println("szukaj:      " + szukaj(tab, 0, n, x));
        System.out.println("szukaj_br:   " + szukaj_br(tab, n, x));
        System.out.println("binarySearch " + Arrays.binarySearch(tab, 0, n, x));
      } 
    }
    
    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  } // main()
  
  // Znajdź element x w tablicy uporządkowanej T
  // w przedziale indeksów d..g-1. Wersja rekurencyjna.
  // Brak elementu ==> zwracana wartość -1.
  static int szukaj(int T[], int d, int g, int x) { 
    if(g==d) return -1;   // Pusta tablica, brak x
  
    int m = (d+g)/2;      // indeks środka 
    if(x==T[m]) return m; // znaleziono x 
  
    if(x < T[m]) 
      return szukaj(T, d, m, x); // rekursja pojedyncza 
    return szukaj(T, m+1, g, x);
  } 

  //Szukanie połówkowe jak wyżej, Bez Rekursji 
  static int szukaj_br(int T[], int n, int x) { 
    int d=0, g=n, m;
    while(d < g) { 
      m = (d+g)/2;            // indeks środka 
      if(x == T[m]) return m;// znaleziono x 
  
      if(x < T[m]) // szukaj w dolnej połowie: zmień g
        g = m;
      else         // szukaj w górnej połowie: zmień d
        d = m+1;
    }
    return -1;  // d>=g, brak x
  }  
}
