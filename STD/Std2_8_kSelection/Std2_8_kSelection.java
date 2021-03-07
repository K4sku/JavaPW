// Wyznaczanie k-minimum przy pomocy metody quickSelect() i quickSort.
// A. Pająk, 2021.

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Std2_8_kSelection {
  static int pole;  // Szerokość wydruku liczb wg zakresu generacji z (p. main())
  static int swaps; // Licznik wymian dla ustalonych n, k
  static int comps; // Licznik porównań dla ustalonych n, k
  static int totalSwaps; // Licznik wszystkich wymian dla ustalonego n
  static int totalComps; // Licznik wszystkich porównań dla ustalonego n
  
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    //Random rnd = new Random();
    Random rnd = new Random(12345);
    
    System.out.println("Testowanie operacji wyboru k-minimum");
    while (true) {
      int n, z, k;
      int[] inx, tab, srt;
      System.out.print("\nLiczba elementów i zakres generacji [0..z]: n z = ");
      n = scn.nextInt(); 
      z = scn.nextInt();
      if(n <= 0 | z<=0) break;

      //pole = String.valueOf(z).length()+1;
      pole = (int)Math.log10(z) + 2;
      totalSwaps = totalComps = 0;
      inx = new int[n]; tab = new int[n]; srt = new int[n];
      for(int i=0; i<n; ++i) {
        inx[i] = i;
        tab[i] = srt[i] = rnd.nextInt(z+1);
      }
      
      // Prezentacja danych
      Arrays.sort(srt); // Dla ułatwienia sprawdzania poprawności
      
      System.out.print("Indx:"); showTab(inx);  // Pozycje
      System.out.print("Data:"); showTab(tab);  // Dane z generatora
      System.out.print("Sort:"); showTab(srt);  // Uporządkowane
      
      System.out.println("Obliczanie k-minimów (k E{0.."+(n-1)+"}"); 
      while(true) {
        System.out.print("k = "); k = scn.nextInt();
        if(k<0 || k>=n) break;
        int[] tt = Arrays.copyOf(tab, n); // Startujemy z oryginalną tablicą
        swaps = comps = 0;
        System.out.print(k+"-minimum = " + quickSelect(tt, k));
        System.out.print(" (swaps: " + swaps + "; comps: " + comps + ")\n");
        totalSwaps += swaps;
        totalComps += comps;
      }
      System.out.println("totalSwaps: " + totalSwaps +
                         " totalComps: " + totalComps);
    }
    
    // Porównanie kosztu obliczania mediany dwoma metodami:
    // 1. quickSort(tab, 0, n-1) i pobranie elementu środkowego
    // 2. quickSelect(tab, n/2).
    System.out.println("\nPorównanie kosztów wyznaczania mediany:");
    System.out.println("  quickSort vs quickSelect");

    while (true) {
      int n, mediana;
      int[] srtTab, selTab;
      System.out.print("\nLiczba elementów (>=10000): n = ");
      n = scn.nextInt(); 
      if(n < 10000) break;

      // Generowanie pary tablic
      srtTab = new int[n]; selTab = new int[n];
      for(int i=0; i<n; ++i)
        //srtTab[i] = selTab[i] = rnd.nextInt();
        srtTab[i] = selTab[i] = i+1;
      
      // Mediana wg quickSort()
      swaps = comps = 0;
      quickSort(srtTab, 0, n-1);
      mediana = srtTab[n/2];
      System.out.print(  "qSort()   med = " + mediana); 
      System.out.println(" swaps: " + swaps + " comps: " + comps); 

      // Mediana wg quickSelect()
      swaps = comps = 0;
      mediana = quickSelect(selTab, n/2);
      System.out.print("qSelect() med = " + mediana); 
      System.out.println(" swaps: " + swaps + " comps: " + comps); 
    }

    scn.close();
    System.out.println("\nKoniec programu");
  } // main()
  
  // k-ty co do wielkości element w tablicy tab
  static int quickSelect(int[] tab, int k) {
    int d = 0;              // Dolny indeks
    int g = tab.length - 1; // Górny indeks
    int indexSep = d;

    //Random rnd = new Random();// Do losowania separatora
    while(g >= d) {                // losowa pozycja separatora w tab[d...g]
      //indexSep = separuj(tab, d, g, rnd.nextInt(g-d+1) + d);
      indexSep = separuj(tab, d, g, (d+g)/2);

      if(indexSep == k) break;  // Znaleziono k-minimum
      
      if(indexSep < k)
        d = indexSep + 1; // Szukaj powyżej separatora
      else 
        g = indexSep - 1; // Szukaj poniżej separatora
    }
    return tab[indexSep];
  }
 
  // Separuje tablicę tab wg elementu separatora s na pozycji p: s=tab[p].
  //             d                 g
  //   tab:[ ...|.... e<s ...|s.....|.....] 
  static int separuj(int[] tab, int d, int g, int p) {
    int s = tab[p];
    swap(tab, p, g);    // Umieść separator na końcu tablicy
    int indexLess = d;  // Index zapisywania elementów < s 
    for(int i = d; i < g; i++) {
      //if(tab[i] < s) {
      if(less(tab[i], s)) {
        swap(tab, i, indexLess);
        ++indexLess;
      }
    }
    swap(tab, g, indexLess); // Separator zraz po elementach "pokonanych".   
    //System.out.printf("**%"+(pole-1) +"d:", s); 
    //showFragment(tab, d, indexLess);
    return indexLess;        // Zwracana pozycja separatora
  }
  
  static void quickSort(int[] T, int d, int g)// zakres indeksów: [d..g]
  { int i, j, s; 
    s = T[(d+g)/2]; // Przykładowy wybór separatora ze środka T
    i = d; j = g; 
    do {
      //while(T[i]<s) ++i; 
      //while(T[j]>s) --j; 
      while(less(T[i], s)) ++i; 
      while(less(s, T[j])) --j; 

      if(i<=j) { 
        swap(T, i, j);
        ++i; --j; 
      } 
    } while(i<j); 
    if(d<j) quickSort(T, d, j); 
    if(i<g) quickSort(T, i, g); 
  } 
   
  static void swap(int[] tab, int i, int j) {
    ++swaps;
    int tmp = tab[i];
    tab[i] = tab[j];
    tab[j] = tmp;
  }

  static boolean less(int a, int b) {
    ++comps;
    return a<b;
  }
 
  // Wydruk tablicy w 1 wierszu.  
  static void showTab(int[] tab) {
    for(int e: tab)
      System.out.printf("%"+pole+"d", e); // statyczna zmienna pole
    System.out.println();
  }
   
  // Wydruk tablicy z wyróżnieniem fragmentu [d..g].  
  static void showFragment(int[] tab, int d, int g) {
    for(int i=0; i<tab.length; ++i)
      if(i<d || i>g)
        System.out.printf("%"+pole+"d ", tab[i]);
      else
        System.out.printf("%"+pole+"d#", tab[i]);
    System.out.println();
  }
   
} // Std2_8_kSelection
