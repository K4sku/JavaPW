// Std2_1_LiczbaRozkladow.java
// Wyznaczanie liczby rozkładów q(m, n) [m, n liczby naturalne] 
// liczby naturalnej m na składniki naturalne nie większe od n.

import java.util.Scanner;

public class Std2_1_LiczbaRozkladow {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    
    System.out.println("Program oblicza liczbę rozkładów liczby");
    System.out.println("naturalnej m na sumy składników <= n.");

    // Pętla konwersacji
    while(true ){
      int m, n;

      System.out.print("\nm n = ");
      m = scn.nextInt();
      n = scn.nextInt();
      if(m <= 0 || n<=0) break;
      licznikZbiorczy = 0;  // Licznik wywo�a� q(m,n)
      liczniki= new int[m][n];
      
      System.out.println("q("+m + ", " + n + ") = " + q(m, n));
      System.out.println("Liczba wywołań q(): " + licznikZbiorczy);

      System.out.println("\nLiczniki wywołań q(i,j)");
      nicePrint(liczniki);
      
    }

//    System.out.print("\nWartości q(i,i) dla i=1..k, k = ");
//    int k = scn.nextInt();
//    for(int i=1; i<=k; ++i) {
//      licznikZbiorczy = 0;
//      System.out.print(qq(i,i) + " ");
//    }
//    System.out.println();
    
    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  }

  static int licznikZbiorczy;
  static int[][] liczniki; // Zerowane przed wywołaniem q()
  
  static int q(int m, int n){
    ++licznikZbiorczy;            // Licznik wywołań funkcji q
    liczniki[m-1][n-1]++; // Liczniki wywołań wg argumentów
    if(m==1 || n==1)
      return 1;
    if(m<=n) 
      return q(m, m-1) +1;
    return q(m, n-1)+q(m-n, n);
  }
  
  static int qq(int m, int n){ // Wersja "czysta"
    if(m==1 || n==1) return 1;
    if(m<=n) return qq(m, m-1) +1;
    return qq(m, n-1)+qq(m-n, n);
  }

  // Wydruk z wyrównaniem kolumn
  static void nicePrint(int[][] T) {
    int m = T.length; // Liczba wierszy
    int lR = 0;       // longest row
    for(int i=0; i<m; ++i)
      if(T[i].length > lR)
        lR = T[i].length;
    
    int[] kolumny = new int[lR];  // Szerokości kolumn
    for(int j=0; j<lR; ++j) {
      int wid = 0;
      for(int i=0; i<m; ++i)
        if(T[i].length>j) {
          int width = (""+T[i][j]).length();
          if(width>wid) wid = width;
        }
      kolumny[j] = wid;
      
    }
    
    // Wydruk
    for(int i=0; i<m; ++i) {
      for(int j=0; j<T[i].length; ++j)
        System.out.printf("%" + kolumny[j] + "d ", T[i][j]);
      System.out.println();
    }   
  }
  
}
/*
Wartości q(i,i) dla i=1..k, k = 20
1 2 3 5 7 11 15 22 30 42 56 77 101 135 176 231 297 385 490 627  

*/