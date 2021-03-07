/* Dla zadanego zbioru N, n liczb naturalnych (można go traktować
   jako zbiór nominałów monet) program oblicza liczbę wariantów
   rozkładów zadanej wartości W na sumy liczb ze zbioru N. 
   Formalnie: rozkład W oznacza istnienie wektora R takiego, że
     N * R = W (mnożenie skalarne dwu wektorów).
   R[i]>=0 oznacza liczbę elementów N[i] użytych w rozkładzie. 
    
   Na życzenie użytkownika program pokazuje wszystkie rozkłady.
   
   Uwaga. Ten program jest uogólnieniem programu obliczania rozkładów
   -----  zadanej liczby naturalnej m na składniki nie większe od 
   liczby naturalnej n (p. Std2_1_LiczbaRozkladow). Wystarczy 
   zdefiniować N = {1,2,3,...,n}. 
   
   Do uzupełnienia:
   ---------------
   0. Uzupełnić program o kontrolę poprawności danych podawanych
      przez użytkownika (wykaz nominałów).
   1. Zmodyfikować metodę variants() tak, aby zmniejszyć liczbę 
      jałowych wywołań rekurencyjnych (przy tym samym wyniku).
      Nowy wariant metody nazwać np. variants1() i użyć jej w taki
      sam sposób jak metody variants().
   2. Zdefiniować metodę generateVariants(int[] N, int n, int W)
      tworzącą listę wszystkich rozkładów, a następnie wydrukować
      te rozkłady w kolejności od najmniejszej liczby składników do
      największej. Na przykład dla nominałów N = {3, 5, 12} oraz W=30
      program powinien generować wykaz:
        4: [2, 0, 2]
        5: [1, 3, 1]
        6: [0, 6, 0]
        7: [6, 0, 1]
        8: [5, 3, 0]
       10: [10, 0, 0] 
   3. Inne modyfikacje / uzupełnienia: wg uznania.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Std2_1a_ProblemReszt {
  
  private static int[] N;  // Tablica nominałów (zbiór n elementów)
  private static int[] R;  // Rozkład: R[i]=liczba monet N[i] w rozkładzie
  private static long licznikWywolan;

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n, W;
    String decyzja;

    System.out.println("Użytkownik określa zbiór n nominałów (>0);");
    System.out.println("program oblicza liczbę rozkładów liczby W>0");
    System.out.println("na sumy składników z tego zbioru nominałów.");

    while(true) {
      System.out.print("\nLiczba nominałów: n = ");
      n = scn.nextInt();
      
      if(n<=0) break; // Koniec programu

      N = new int[n]; 
      R = new int[n];  
      System.out.println("Podaj wykaz nominałów >0 (" + n +" liczb(y))");
      for(int i=0; i<n; ++i) {
        N[i] = scn.nextInt();
        if(N[i] <= 0) --i;
      }
      
      System.out.println("Nominały N = " + Arrays.toString(N));
   
      // ToDo: kontrola wykazu N (zbiór - bez powtórzeń)
      
      while(true) {
        System.out.print("\nWartość do rozkładu: W = ");
        W = scn.nextInt();
        
        if(W<=0) break; // Koniec z aktualnym zestawem N

        licznikWywolan = 0;
        int v = variants(N, n, W);
        System.out.println("liczba rozkładów: " + v);
        System.out.println("licznikWywolan: " + licznikWywolan);

        if(v>0) {
          System.out.print("Generować rozkłady? (t/n): ");
          decyzja = scn.next();
          if(decyzja.equals("t")) {
            System.out.println("\nROZKŁADY");
            showVariants(N, n, W);
          }
        }         
      }
    }
    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  }

  //-----------------
  // Oblicza liczbę sposobów wyrażenia wartości W 
  // jako sumę nominałów (monet) z N[0..n-1].
  static int variants(int[] N, int n, int W) {
    ++licznikWywolan;
    if((W<0) || (n<=0 && W>0)) return 0;  // Brak rozwiązań

    if(W==0) return 1;  // 1 rozwiązanie (pusty zestaw monet)

    // Rekursja: warianty bez monety N[n-1] + warianty z nią.
    return variants(N, n-1, W) + variants(N, n, W-N[n-1]);
  }

  //-----------------
  // R: tablica (statyczna) z generowanym rozkładem; 
  static void showVariants(int[] N, int n, int W) {
    
    if((W<0) || (n<=0 && W>0)) return;// Brak rozwiązań
    if(n==1 && (W%N[0] != 0))  return;// W niepodzielne przez jedyny nominał 
    
    if(n==1 && (W%N[0] == 0)){  // W jest podzielne przez jedyny nominał 
      R[0] += W/N[0]; 
      print(R);
      R[0] -= W/N[0]; // Wycofaj po prezentacji (jesteśmy w rekursji!)
      return;
    }
        
    if(W==0) {  // Rozkład kompletny
      print(R);
      return;
    } 
    
    // Rekursja: pokaż warianty bez monety N[n-1]
    // a następnie warianty z monetą N[n-1].
    if(n>1) showVariants(N, n-1, W);
    
    R[n-1]++;   // Zaznacz użycie monety w R
    showVariants(N, n, W-N[n-1]);
    R[n-1]--;   // Wycofaj monetę po prezentacji (jesteśmy w rekursji)
  }

  //-----------------
  //Oblicza sumę elemntów T
  static int sum(int[] T) { 
    int suma = 0;
    for(int r : T) suma += r;
    return suma;
  }
  
  //-----------------
  static void print(int[] R) {
    System.out.printf("%3d: %s%n", sum(R), Arrays.toString(R));
  }

}

/* Przykład wykonania: nominały monet dla strefy euro; rozkłady 5EUR;
 *                     nominały monet polskich, rozkład 10 zł.


Użytkownik określa zbiór n nominałów (>0);
program oblicza liczbę rozkładów liczby W>0
na sumy składników z tego zbioru nominałów.

Liczba nominałów: n = 8
Podaj zbiór nominałów (8 liczb)
1 2 5 10 20 50 100 200

Wartość do rozkładu: W = 500
liczba rozkładów: 6295434
licznikWywolan: 1026668093
Generować rozkłady? (t/n): n

Wartość do rozkładu: W = 0

Liczba nominałów: n = 9
Podaj wykaz nominałów >0 (9 liczb(y))
1 2 5 10 20 50 100 200 500
Nominały N = [1, 2, 5, 10, 20, 50, 100, 200, 500]

Wartość do rozkładu: W = 1000
liczba rozkładów: 327631321
licznikWywolan: 95164909367
Generować rozkłady? (t/n): n

Wartość do rozkładu: W = 0

Liczba nominałów: n = 0

KONIEC PROGRAMU
*/