// 1. Testowanie 5 podstawowych algorytmów sortowania.
// 2. Porównanie czasów sortowania quicksort() i Arrays.sort().

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Std2_3_SortowanieTablicowe {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rnum = new Random();
    
    System.out.println("1. Testowanie 5 podstawowych algorytmów sortowania.");
    System.out.println("2. Porównanie czasów quickSort(), mergeSort() i Arrays.sort()");

    // Pętla konwersacji
    while(true ){
      int n, z;
      int[] T, X;

      System.out.print("\nRozmiar tablicy: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      T = new int[n];
      System.out.print("Jaki zakres generacji 0..z: z = ");
      z = scn.nextInt();
        
      for(int i=0; i<n; ++i){
        int r = rnum.nextInt(z+1);
        System.out.print(r + " ");
        T[i] = r;
      }
      System.out.println();
      
      // Sortowanie 1: selSort_itr()
      System.out.println("selSort_iter");
      X = Arrays.copyOf(T,  T.length);  // Kopia tablicy
      selSort_iter(X);
      show(X);
      
      // Sortowanie 2: bubbleSort()
      System.out.println("bubbleSort");
      X = Arrays.copyOf(T,  T.length);  // Kopia tablicy
      bubbleSort(X);
      show(X);     

      // Sortowanie 3: insertionSort()
      System.out.println("insertionSort");
      X = Arrays.copyOf(T,  T.length);  // Kopia tablicy
      insertionSort(X);
      show(X);     

      // Sortowanie 4: quickSort()
      System.out.println("quickSort");
      X = Arrays.copyOf(T,  T.length);  // Kopia tablicy
      quickSort(X, 0, X.length-1);
      show(X);
      
      // Sortowanie 5: mergeSort()
      System.out.println("mergeSort");
      X = Arrays.copyOf(T,  T.length);  // Kopia tablicy
      mergeSort(X);
      show(X);
      
    }

    // Porównanie czasów wykonania
    System.out.println("\nPorównanie quickSort(), mergeSort() i Arrays.sort()");
    while(true ){
      int n;
      int[]     A, B, C;  // quick, merge, sort 
      Integer[] P, Q, R;  // quick, merge, sort
      long t1, t2, t3, t4, t5, t6, t7;  // 6 interwałów 

      System.out.print("\nRozmiar tablicy (>=100000): n = ");
      n = scn.nextInt();
      if(n<100000) break;
      
      A = new int[n]; 
      B = new int[n];
      C = new int[n];
      
      P = new Integer[n]; 
      Q = new Integer[n];
      R = new Integer[n];
      
      for(int i=0; i<n; ++i){
        //int r = rnum.nextInt();
        int r = rnum.nextInt(32767);
        A[i] = B[i] = C[i] = r;
        P[i] = Q[i] = R[i] = r;
      }
      
      // Sortowanie tablic int[]
      t1 = System.currentTimeMillis();
      quickSort(A, 0, n-1);
      t2 = System.currentTimeMillis();
      mergeSort(B);
      t3 = System.currentTimeMillis();
      Arrays.sort(C);
      t4 = System.currentTimeMillis();

      // Sortowanie tablic Integer[]
      quickSort(P, 0, n-1);
      t5 = System.currentTimeMillis();
      mergeSort(Q);
      t6 = System.currentTimeMillis();
      Arrays.sort(R);
      t7 = System.currentTimeMillis();
      
      System.out.println("int:     quickSort():   "+(t2-t1)+"[ms]");
      System.out.println("int:     mergeSort():   "+(t3-t2)+"[ms]");
      System.out.println("int:     Arrays.sort(): "+(t4-t3)+"[ms]");

      System.out.println("Integer: quickSort():   "+(t5-t4)+"[ms]");
      System.out.println("Integer: mergeSort():   "+(t6-t5)+"[ms]");
      System.out.println("Integer: Arrays.sort(): "+(t7-t6)+"[ms]");
    }

    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  } // main()
  
  static void show(int[] X) {
    for(int e: X)
      System.out.print(e + " ");
    System.out.println();
  }

  // SORTOWANIE 1
  static void selSort_iter(int[] T)
  { int n = T.length;
    int imax;
    for(int i=n-1; i>0; --i){ 
      // Oblicz imax = max_index(T, i+1); 
      imax = 0;
      for(int j=1; j<=i; ++j)
        if(T[j]>T[imax]) imax = j;
      swap(T, imax, i);
    }
  } // Złożoność O(n^2)

  // SORTOWANIE 2
  static void bubbleSort(int T[]) 
  { int n = T.length;
    boolean wymiana; // Czy w ostatnim skanowaniu była wymiana? 
    do {
      wymiana = false; 
      for(int i=0; i<n-1; ++i) 
        if(T[i]>T[i+1]) {
          swap(T, i, i+1); 
          wymiana = true; 
        }
    } while(wymiana); 
  } // Złożoność: O(n^2) 

  // SORTOWANIE 3
  static void insertionSort(int T[])
  { int n = T.length, x, j;
    for(int i=1; i<n; ++i) 
    { x = T[i];
      for(j=i-1; j>=0 && T[j]>x; --j) 
        T[j+1] = T[j];
      T[j+1] = x;
    }
  } // Złożoność: O(n^2) 
  
  // SORTOWANIE 4
  static void quickSort(int[] T, int d, int g)// zakres indeksów: d..g
  { int i, j, s; 
    s = T[(d+g)/2]; // Przykładowy wybór separatora ze środka T
    i = d; j = g; 
    do 
    { while(T[i]<s) ++i; 
      while(T[j]>s) --j; 
      if(i<=j) { 
        swap(T, i, j);
        //int tmp=T[i]; T[i]=T[j]; T[j]=tmp;
        ++i; --j; 
      } 
    } while(i<j); 
    if(d<j) quickSort(T, d, j); 
    if(i<g) quickSort(T, i, g); 
  } 
  
  // SORTOWANIE 4-prim (Integer zamiast int)
  static void quickSort(Integer[] T, int d, int g)// zakres indeks�w: d..g
  { int i, j, s; 
    s = T[(d+g)/2]; // Przykładowy wybór separatora ze środka T
    i = d; j = g; 
    do 
    { while(T[i]<s) ++i; 
      while(T[j]>s) --j; 
      if(i<=j) { 
        swap(T, i, j);
        //int tmp=T[i]; T[i]=T[j]; T[j]=tmp;
        ++i; --j; 
      } 
    } while(i<j); 
    if(d<j) quickSort(T, d, j); 
    if(i<g) quickSort(T, i, g); 
  } 
  
  // SORTOWANIE 5
  static void mergeSort(int T[]) {
    int n = T.length;
    if(n<=1) return; // Nie ma nic do roboty
    int m1 = n/2, m2 = n-m1;
    int[] A = new int[m1], B = new int[m2];
    for(int i=0; i<m1; ++i) A[i] = T[i];
    for(int i=0; i<m2; ++i) B[i] = T[i+m1];
    mergeSort(A);
    mergeSort(B);
    
    // Scalanie
    int i1=0, i2=0, i=0;  // i: indeks odbiorczy w T
    while(i1<m1 && i2<m2)  
      if(A[i1]<B[i2]) 
        T[i++]=A[i1++]; 
      else 
        T[i++]=B[i2++];
    
    // Jedna z list wyczerpana 
    while(i1<m1) T[i++] = A[i1++];
    while(i2<m2) T[i++] = B[i2++];
  }
  
  static void mergeSort(Integer T[]) {
    int n = T.length;
    if(n<=1) return; // Nie ma nic do roboty
    int m1 = n/2, m2 = n-m1;
    Integer[] A = new Integer[m1];
    Integer[] B = new Integer[m2];
    for(int i=0; i<m1; ++i) A[i] = T[i];
    for(int i=0; i<m2; ++i) B[i] = T[i+m1];
    mergeSort(A);
    mergeSort(B);
    
    // Scalanie
    int i1=0, i2=0, i=0;  // i: indeks odbiorczy w T
    while(i1<m1 && i2<m2)  
      T[i++] = (A[i1]<B[i2])? A[i1++] : B[i2++];
    
    // Jedna z list wyczerpana 
    while(i1<m1) T[i++] = A[i1++];
    while(i2<m2) T[i++] = B[i2++];
  }
  
  // SORTOWANIE 6 (p.Test MinHeap)

  static void swap(int[] X, int i, int j) {
    int tmp = X[i];
    X[i] = X[j];
    X[j] = tmp;
  }
  
  static void swap(Integer[] X, int i, int j) {
    int tmp = X[i];
    //Integer tmp = X[i];
    X[i] = X[j];
    X[j] = tmp;
  }
}
