import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Std1_1_MaksymalnaSuma {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rnum = new Random();
 
    System.out.println("Program wyznacza podtablicę o maksymalnej >=0 sumie elementów."); 
    System.out.println("Jeżeli wszystkie elementy <= 0, to podtablica jest pusta.");
    
    // Pętla konwersacji
    while(true ){
      int n, z;
      int X[];

      System.out.print("\nRozmiar tablicy: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      X = new int[n];
      System.out.print("Generować dane (t/n): ");
      String decyzja = scn.next();

      if(decyzja.equals("t")) {
        System.out.print("Jaki zakres generacji -z..z: z = ");
        z = scn.nextInt();
        
        for(int i=0; i<n; ++i){
          int r = rnum.nextInt(2*z+1) -z;
          System.out.print(r + "  ");
          X[i] = r;
        }
        System.out.println();
      }
      else { // Wprowadzanie ręczne
        System.out.println("Podaj liczby z klawiatury oddzielone spacjami (" + n + ")");
        for(int i=0; i<n; ++i)
          X[i] = scn.nextInt();
      }
      
      // Obliczanie maksymalnej sumy
      int[] wynik = maxSum1(X);
      
      // Prezentacja wyników
      System.out.println("\nMaksymalna suma wg maxSum1(): " + wynik[2]);
      System.out.println("Podtablica w zakresie indeksów: [" 
                         + wynik[0] + ".." + wynik[1] + ")");
      for(int i=0; i<n; ++i){
        System.out.print(X[i]);
        if(i>=wynik[0] && i<wynik[1]) 
          System.out.print("* ");
        else
          System.out.print("  ");
      }
      System.out.println();
      
      System.out.println("\nMaksymalna suma wg maxSum2():   " + maxSum2(X, 0, X.length));
      System.out.println("\nMaksymalna suma wg maxSumOpt(): " + maxSumOpt(X));
      
    }

    // Funkcje s2(n) i s3(n)
    System.out.println("Porównanie wyników funkcji s2(n), s3(n) z formułami\n");
    System.out.println(" n  s2(n) n(n+1)/2  s3(n)  n(n+1)(n+2)/6");
    System.out.println("-----------------------------------------");
    for(int n=1; n<11; ++n)
      System.out.printf("%2d%7d%7d%9d%9d%n", 
          n,s2(n), n*(n+1)/2,s3(n), n*(n+1)*(n+2)/6);
    
    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  } // main()
  

  // Znajduje indeksy lewy, prawy takie, że suma elementów
  // X[lewy] .. X[prawy-1] jest maksymalna, >=0. Zwraca indeksy i sumę
  // w wynikowej tablicy 3 elementowej {lewy, prawy, smax}. 
  // Jeżeli lewy==prawy, to znaczy podtablica jest pusta, smax = 0.
  static int[] maxSum1(int X[]){
    int smax = 0, n = X.length;
    int lewy = 0, prawy = 0;    // Domniemanie: przypadek podtablicy pustej

    for(int d=0; d<n; ++d){
      int suma = 0;
      for(int g=d; g<n; ++g){
        suma += X[g];
        if(smax < suma){ 
          smax = suma; lewy = d; prawy = g+1;
        }
      }
    }
    return new int[]{lewy, prawy, smax};
  }

  // Wersja rekurencyjna O(n log n). Zwraca tylko maksymalną sumę.
  static int maxSum2(int X[], int d, int g) {
    int maxL, maxP, maxA, maxB, suma;
  
    if(d>=g) return 0;  // Pusta podtablica
    if(d == g-1)        // 1 element
      return Integer.max(X[d], 0);
  
    int m = (d+g)/2;    // indeks �rodka

    //Znajdź maks. na lewo od m
    suma = maxL= 0;
    for(int i=m-1; i>=d; --i) {
      suma += X[i];
      maxL = Integer.max(suma, maxL);
    }
  
    //Znajdź maks. na prawo od m
    suma=maxP=0;
    for(int i=m; i<g; i++)
    { suma += X[i];
      maxP = Integer.max(suma, maxP);
    }
    
    maxA = maxSum2(X, d, m);  // Rekursja w lewo
    maxB = maxSum2(X, m, g);  // Rekursja w prawo
    return Integer.max(Integer.max(maxA, maxB), maxL + maxP);
  }

  // Wersja optymalna (bez wyznaczania indeksów)
  static int maxSumOpt(int X[])
  { int maxG = 0,  // Maksymalna suma globalna 
        maxL = 0;  // Maksymalna suma lewostronnie

    for(int i=0; i<X.length; ++i)
    { // maxG, maxL: maksymalne sumy w
      // X[0..i-1], globalna, lewostronna

      maxL = Integer.max(0, maxL+X[i]);
      maxG = Integer.max(maxL, maxG);
    }
    return maxG;
  }

  static int s2(int n) {
    int s=0;
    for(int i=0; i<n; ++i)
      for(int j=i; j<n; ++j)
        ++s;
    return s;
  }
  
  static int s3(int n) {
    int s=0;
    for(int i=0; i<n; ++i)
      for(int j=i; j<n; ++j)
        for(int k=i; k<=j; ++k)
          ++s;
    return s;
  }
}
