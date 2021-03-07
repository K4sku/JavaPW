import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Std2_6_GenericMinHeap {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random  rnd = new Random();
/*    
    Character[] tab = {'P','R','O','G','R','A','M'};
    MinHeap<Character> m = new MinHeap<>(7); 
    MinHeap<Character> p = new MinHeap<>(7);
    m.makeHeap(tab); 
    p.pushHeap(tab);
    System.out.println(m);
    System.out.println(p);
    
    Character[] kopiec = {'K','O','P','I','E','C'};
    MinHeap<Character> mpush = new MinHeap<>(6); 
    MinHeap<Character> mmake = new MinHeap<>(6); 
    mmake.makeHeap(kopiec);
    mpush.pushHeap(kopiec);
    System.out.println(mmake);
    System.out.println(mpush);
*/
    System.out.println("Eksperymenty z kopcem MinHeap<Character>");
    System.out.println("Słowo jednoliterowe kończy eksperyment.");
    while(true) { // Konwersacja
      System.out.print("\nNowy kopiec dla słowa: slowo = ");
      String slowo = scn.next();
      int n = slowo.length();
      
      Character[] cTab = new Character[n];
      for(int i=0; i<n; ++i)
        cTab[i] = slowo.charAt(i);    

      MinHeap<Character> mhcmake = new MinHeap<>(n); 
      MinHeap<Character> mhcpush = new MinHeap<>(n); 

      mhcmake.makeHeap(cTab);     
      mhcpush.pushHeap(cTab);
      System.out.println("makeHeap: " + mhcmake);
      System.out.println("pushHeap: " + mhcpush);
      
      if(n==1) break;
    }
    
    System.out.println("\nEksperymenty z kopcem MinHeap<Integer>");
    while(true) { // Konwersacja
      System.out.print("\nNowy kopiec o rozmiarze n: n = ");
      int n = scn.nextInt();
      if(n <= 0) break;
      
      Integer[] iTab = new Integer[n];
      Integer[] sorted;

      System.out.print("Zakres generacji 0..z (z<=0 ==> dane z klawiatury) : z = ");
      int z = scn.nextInt();

      if(z<=0) {
        System.out.println("Podaj " + n + " liczb");
        for(int i=0; i<n; ++i)
          iTab[i] = scn.nextInt();
      }
      else {
        for(int i=0; i<n; ++i)
          iTab[i] = rnd.nextInt(z+1);
      }
      System.out.println("\nDane:     " + Arrays.toString(iTab));

      MinHeap<Integer> mhimake = new MinHeap<>(0); 
      MinHeap<Integer> mhipush = new MinHeap<>(n); 

      mhimake.makeHeap(iTab);     
      mhipush.pushHeap(iTab);
      System.out.println("makeHeap: " + mhimake);
      System.out.println("pushHeap: " + mhipush);

      sorted =  mhimake.heapSort();
      System.out.println("heapSort: " + Arrays.toString(sorted));
    }
    
    scn.close();
    System.out.println("\nKONIEC PROGRAMU");
  }
}

  class MinHeap<E> {
  private E[] H;        // Tablica elementów
  private int capacity; // Aktualna pojemność kopca
  private int n;        // Liczba elementów w kopcu <= capacity
  
  @SuppressWarnings("unchecked")
  public MinHeap(int c){ // Kopiec pusty o pojemności c
    capacity = c;
    H = (E[])new Object[c]; // Nie można utworzyć new E[c];
    n = 0;
  }
  
  public MinHeap(E[] A){// Kopiec wg tablicy elementów
    makeHeap(A);
  }
  
  // Dokładaj do kopca elementy z tablicy A.
  public void pushHeap(E[] A){
    for(int i=0; i<A.length; ++i)
      push(A[i]);
  }
  
  // Wypełnij kopiec "hurtem" wg tablicy A.
  public void makeHeap(E[] A){
    H = Arrays.copyOf(A, A.length);
    n = capacity = A.length;
    
    // Wymuszenie warunków kopca
    for(int i=(n-2)/2; i>=0; --i)
      moveDown(i);
  }
  
  public boolean empty() { return n==0; }
  
  public int size() { return n; }
  
  public int maxSize() { return capacity; }

  public void push(E e){ 
    if(n==capacity) resize();
    H[n++] = e; 
    moveUp(); 
  }

  public E  top() { 
    assert n>0; 
    return (E)H[0]; 
  }

  public E  pop(){
    E min = (E)H[0];
    H[0] = H[n-1];
    H[--n] = null;  // zwolnij obiekt
    moveDown(0);
    return min;
  }

  public String toString() {
    if(n==0) return "[]";
    String s = "[";
    for(int i=0; i<n-1; ++i){
      s += H[i] + ", ";      
    }
    s += H[n-1] + "]";
    return s;
  }

  @SuppressWarnings("unchecked")
  public E[] heapSort() {
    E[] tmp = (E[]) Arrays.copyOf(H, n);  // Do odtworzenia kopca
    E[] sorted = (E[]) new Object[n];
    
    for(int i=0; n>0; ++i) {
      sorted[i] = pop();
    }
 
    // Przywróć stan sterty
    H = Arrays.copyOf(tmp, tmp.length);
    n = tmp.length;
    //System.out.println("*** " + Arrays.toString(sorted));
    return (E[]) Arrays.copyOf(sorted, n, H.getClass());
  }

  private void resize() {
    int nc = 2*capacity+1;
    //System.out.println("--- " + capacity);
    H = Arrays.copyOf(H, nc);
    capacity = nc;
  }

  @SuppressWarnings("unchecked")
  private void moveUp(){
    E x = H[n-1];
    int pos = n-1;  // Indeks ostatniego elementu w kopcu
    int up  = (pos-1)/2;
    while(pos>0 && 
          ((Comparable<? super E>)H[up]).compareTo(x)>0){
      swap(H, pos, up);
      pos = up;
      up = (pos-1)/2;
    }
  }

  @SuppressWarnings("unchecked")  
  private void moveDown(int k) {
    // Wymuszenie możliwości stosowania compareTo()
    // do elementu H[k] "przetaczanego" w dół
    Comparable<? super E> e = (Comparable<? super E>)H[k];    
     
    int j=2*k+1;  // Indeks lewego potomka
    while(j<n) {
      if(j<n-1 &&
         ((Comparable<? super E>)H[j]).compareTo(H[j+1])>0)
        ++j;      // j: indeks mniejszego potomka
      if(e.compareTo((H[j]))<=0)
        break;     // Już OK.
      H[k] = H[j];
      k = j;       // W dół do nast�pnego poziomu
      j = 2*k+1;
    }
    H[k] = (E)e;  // Osadzenie obiektu w odpowiednim miejscu
  }
  
  private static <E> void swap(E[] T, int i, int j) {
    E e  = T[i];
    T[i] = T[j];
    T[j] = e;
  }
}
