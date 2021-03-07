import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Std2_4_BasicMinHeap {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rnum = new Random();
    
    System.out.println("Podstawowy kopiec MinHeap dla elementów int.");

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
      
      for(int i=0; i<n; ++i)
        T[i] = rnum.nextInt(z+1);
      System.out.println("Dane:  "+Arrays.toString(T));
      
      // Tworzenie kopca
      MinHeapInt mhi = new MinHeapInt(T); 
      System.out.println("Heap:  "+mhi);
      X = mhi.heapSort();
      System.out.println("sorted:" + Arrays.toString(X));
    }

    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  } // main()

}

class MinHeapInt {
  private int[] v;    // Wewnętrzny kontener kopca
  private int n;      // Aktualny rozmiar kopca 
  private int maxsize;// Dopuszczalna pojemność kopca

  public MinHeapInt(int[] T) { // Inicjalizacja tablicą T
    v = Arrays.copyOf(T, T.length);
    n = maxsize = T.length;
    makeHeap();
  }

  public MinHeapInt(int msiz) { // Rezerwacja miejsca
    v = new int[msiz];
    n = 0; maxsize = msiz;
  }

  int   top()   { return v[0]; }
  int   size()  { return n; }
  void  clear() { v = null; n = 0; }

  public int pop() {
    int e = v[0];
    if(size()>0)
      v[0] = v[--n];
    moveDown(0);
    return e;
  }

  public void push(int e) {
    if(n==maxsize)
      throw new ArrayIndexOutOfBoundsException(n);
    v[n++] = e;
    moveUp();
  }

  public String toString() {
    return Arrays.toString(v);
  }
  
  public int[] heapSort() { // Zwraca tablicę posortowaną
    int[] tmp = Arrays.copyOf(v, n);  // Do odtworzenia kopca
    int[] wynik = new int[n];
    
    for(int i=0; n>0; ++i) {
      wynik[i] = pop();
    }
    // Przywr�� stan kopca
    v = Arrays.copyOf(tmp, tmp.length);
    n = tmp.length;
    return wynik;
  }
  
  private void makeHeap()
  { if(n<=1) return; // Wektor pusty lub 1-elementowy
    for(int i=(n-2)/2; i>=0; --i) 
      moveDown(i);
  }
  
  private void moveUp()
  // Do v (z prawidłową stertą) dodano element e;
  // należy ustanowić rozszerzoną stertę.
  { int k = n-1;      // Indeks ostatniego elementu
    if(k==0) return;  // Nic do roboty dla elementu #0

    int j = (k-1)/2;   // j = indeks rodzica
    do
    { int e = v[k], up = v[j];
      if(up<e) break; // Warunek sterty spełniony 
      v[k] = up;      // Wymień, idź piętro wyżej
      v[j] = e;
      k = j;
      if(k==0) break;
      j = (k-1)/2;
    } while(true);
  }

  private void moveDown(int k)
  // Zakładając, że poddrzewa  z korzeniami 2*k+1 oraz 2*(k+1) są prawidlowymi
  // stertami, ustanowić prawidlową stertę o rozmiarze n z korzeniem k.
  { 
    if(n==0) return;
    int j = 2*k+1; // j: indeks lewego potomka wezla k
    int e = v[k];

    while(j<n) {
      if(j<n-1 && v[j+1]<v[j]) j++; // j = indeks mniejszego potomka
      if(e<v[j]) break;             // Sterta z korzeniem k OK
      v[k] = v[j];            // Zejście do nastepnego poziomu
      k = j;
      j = 2*k+1;
    }
    v[k] = e;
  }
} // MinHeapInt
