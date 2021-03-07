import java.util.*;
import java.lang.Math;

public class Std2_7_GenericArrayHeap {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rg = new Random();
    
    System.out.println("Testowanie klasy ArrayHeap<E> dla typów Integer i Point.");
    System.out.println("Przyrostki w wydrukach:");
    System.out.println("  _i: Integer, porządek naturalny");
    System.out.println("  _p: Point, porządek naturalny");
    System.out.println("  _r: Point, porządek wg Reverse (odwrócenie naturalnego)");
    System.out.println("  _d: Point, porządek wg OrtoDist (dystans ortogonalny od (0,0))");

    // P�tla konwersacji dla typu Integer i Point
    while(true) {
      int n, z;
      Integer[] di, si; // Tablice danych i po sortowaniu
      Point[]   dp, sp; // Tablice danych i po sortowaniu
      Point[]   dr, sr; // Tablice dla komparatora Reverse<Point>
      Point[]   dd, sd; // Tablice dla komparatora PreferY<Point>
      //Point[]   dx;

      System.out.print("\nRozmiar tablic: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      di = new Integer[n];
      dp = new Point[n];
      dr = new Point[n];
      dd = new Point[n];
      //dx = new Point[n];
      
      //System.out.print("Jaki zakres generacji -z..z: z = ");
      System.out.print("Jaki zakres generacji 0..z: z = ");
      z = scn.nextInt();
      
      for(int i=0; i<n; ++i) {
        //di[i] = rg.nextInt(2*z+1)-z;
        //dp[i] = new Point(rg.nextInt(2*z+1)-z, rg.nextInt(2*z+1)-z);
        di[i] = rg.nextInt(z+1);
        dp[i] = new Point(rg.nextInt(z+1), rg.nextInt(z+1));

        dr[i] = dp[i];
        dd[i] = dp[i];
        //dx[i] = dp[i];  // Pomocnicza
      }
      System.out.println("Dane:   "+Arrays.toString(di));
      System.out.println("Dane:   "+Arrays.toString(dp));
/*      
      // Różne warianty sortowania punktów
      Point[] d1 = Arrays.copyOf(dx, dx.length);
      System.out.println("unsorted   : "+Arrays.toString(d1));
      Arrays.sort(d1);
      System.out.println("sortNatural: "+Arrays.toString(d1));
      
      d1 = Arrays.copyOf(dx, dx.length);
      Arrays.sort(d1, new PreferY());
      System.out.println("sortPreferY: "+Arrays.toString(d1));
      
      d1 = Arrays.copyOf(dx, dx.length);
      Arrays.sort(d1, 
          (p1,p2) -> (p1.getX() + p1.getY()) - (p2.getX() + p2.getY()));
      System.out.println("sortOrtoDst: "+Arrays.toString(d1));
*/      
      // Tworzenie kopcy
      Heap<Integer> hi = new ArrayHeap<>(di); 
      Heap<Point>   hp = new ArrayHeap<>(dp); 
      Heap<Point>   hr = new ArrayHeap<>(dr, new Reverse());
      Heap<Point>   hd = new ArrayHeap<>(dd, new OrtoDist()); 
      si = hi.heapSort();
      sp = hp.heapSort();
      sr = hr.heapSort();
      sd = hd.heapSort();
      System.out.println("heap_i: "+hi);
      System.out.println("sort_i: "+Arrays.toString(si));
      System.out.println("heap_p: "+hp);
      System.out.println("sort_p: "+Arrays.toString(sp));
      System.out.println("heap_r: "+hr);
      System.out.println("sort_r: "+Arrays.toString(sr));
      System.out.println("heap_d: "+hd);
      System.out.println("sort_d: "+Arrays.toString(sd));
      
      // Te wydruki powinny pokazać, że po sortowaniu kopce są bz.
      System.out.println("\nPo sortowaniu zawartość kopców bz.");
      System.out.println("heap_i: "+hi);
      System.out.println("heap_p: "+hp);
      System.out.println("heap_r: "+hr);
      System.out.println("heap_d: "+hd);
    }

/*
    // Powtórka testu dla komparatora PreferY<Point>
    // (współrzędna Y jest bardziej znacząca).
    // Dwie sterty budowane inkrementalnie i hurtem. 
    while(true) {
      int n, z;
      Point[]   dpy, spy1, spy2; // Tablice danych i po sortowaniu

      System.out.print("\nRozmiar tablicy: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      dpy = new Point[n];
      
      System.out.print("Jaki zakres generacji 0..z: z = ");
      z = scn.nextInt();
      
      for(int i=0; i<n; ++i) {
        dpy[i] = new Point(rg.nextInt(z+1), rg.nextInt(z+1));
      }
      System.out.println("Dane:   "+Arrays.toString(dpy));
      
      // Tworzenie kopcy
      Heap<Point>   hpy1 = new ArrayHeap<>(dpy, new PreferY()); 
      Heap<Point>   hpy2 = new ArrayHeap<Point>(new Point[0], new PreferY());
      for(int i=0; i<n; ++i) 
        hpy2.push(dpy[i]);

      System.out.println("heap_y1: "+hpy1);
      System.out.println("heap_y2: "+hpy2);
      spy1 = hpy1.heapSort();
      spy2 = hpy2.heapSort();
      System.out.println("sort_y1: "+Arrays.toString(spy1));
      System.out.println("sort_y2: "+Arrays.toString(spy2));
      
      //showHeap(hpy1, "heap_y1");
      //showHeap(hpy2, "heap_y2");
    }
*/
    // MaxHeap przez wstrzyknięcie komparatora
    System.out.println("\nMaxHeap<Integer>");
    while(true) {
      int n, z;
      Integer[] di, si; // Tablice danych i po sortowaniu

      System.out.print("\nRozmiar tablicy: n = ");
      n = scn.nextInt();
      if(n <= 0) break;
      
      di = new Integer[n];
      
      System.out.print("Zakres generacji 0..z: z = ");
      z = scn.nextInt();
      
      for(int i=0; i<n; ++i) 
        di[i] = rg.nextInt(z+1);
      
      System.out.println("Dane:    "+Arrays.toString(di));

      // Tworzenie kopca MaxHeap
      Heap<Integer> maxHeap = 
          new ArrayHeap<>(di, (a, b)-> b.compareTo(a)); 
      si = maxHeap.heapSort();

      System.out.println("maxheap: "+maxHeap);
      System.out.println("sorted:  "+Arrays.toString(si));
      
    }
    System.out.println("\nKONIEC PROGRAMU");
    scn.close();
  } // main()
  
  static public <E> void showHeap(Heap<E> h, String header) {
    System.out.println("--- "+header+" ---");
    System.out.println("capacity: " + h.capacity());
    System.out.println("size:     " + h.size());
    System.out.println("contents: " + h);
  }
  
  static <E> void show(E[] X) {
    for(E e: X)
      System.out.print(e + " ");
    System.out.println();
  }


}

interface Heap<E> {
  boolean push(E e);
  E peek();
  E pop();
  void clear();
  int size();
  int capacity();
  E[] heapSort();
}

class ArrayHeap<E> implements Heap<E>{
  private Comparator<? super E> cmp;
  private E[] H;
  private int size = 0;

  @SuppressWarnings("unchecked")
  public ArrayHeap() {  // 
    H = (E[]) new Object[0];
    cmp = null;   // Porządek "naturalny" wg Comparable<E>
  }

  //@SuppressWarnings("unchecked")
  public ArrayHeap(ArrayHeap<? extends E> h) {
    if(h.getClass() == ArrayHeap.class) {
      this.cmp = (Comparator<? super E>) h.comparator();
      this.H = (E[])h.toArray();
      this.size = h.size();
    } 
    else throw new IllegalArgumentException();
  }

  public ArrayHeap(E[] a) { this(a, null); }

  public ArrayHeap(E[] a, Comparator<? super E> cmp) {
    this.cmp = cmp;  
    this.H = a;
    this.size = a.length;
    makeHeap();
  }

  public boolean push(E e) {
    if(e == null)
      throw new NullPointerException();
    if(size == H.length)
      resize();
    H[size++] = e;
    if(size>1)
      moveUp();
    return true;
  }

  public E peek() {
      return (size == 0) ? null : (E) H[0];
  }

  public Object[] toArray() {
      return Arrays.copyOf(H, size);
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
      final int size = this.size;
      if(a.length < size)
        // Make a new array of a's runtime type, but my contents:
        return (T[]) Arrays.copyOf(H, size, a.getClass());
      System.arraycopy(H, 0, a, 0, size);
      if(a.length > size)
          a[size] = null;
      return a;
  }

  public int size() { return size; }

  public int capacity() {
    return H.length;
  }
  
  public void clear() {
      for(int i = 0; i < size; i++)
          H[i] = null;
      size = 0;
  }

  public E pop() {
      if(size == 0)
        return null;
      int s = --size;
      E e = (E) H[0];
      H[0] = H[s];
      H[s] = null;
      if(s != 0)
        moveDown(0);
      return e;
  }

  public Comparator<? super E> comparator() {
    return cmp;
  }

  public String toString() {
    return Arrays.toString(Arrays.copyOf(H, size));
  }

  @SuppressWarnings("unchecked")
  public E[] heapSort() {
    E[] tmp = (E[]) Arrays.copyOf(H, size);  // Do odtworzenia kopca
    E[] sorted = (E[]) new Object[size];
    
    for(int i=0; size>0; ++i) {
      E e = pop();
      sorted[i] = e;
    }
 
    // Przywróć stan sterty
    H = Arrays.copyOf(tmp, tmp.length);

    size = tmp.length;
    //System.out.println("*** " + Arrays.toString(sorted));
    return (E[]) Arrays.copyOf(sorted, size, H.getClass());
  }

  private void resize() {
    int n = H.length;
    //System.out.println("--- " + n);
    H = Arrays.copyOf(H, 2*n+1);
  }

  private void moveUp() {
    if(cmp != null)
      moveUpCmp();
    else
      moveUpNatural();
  }

  @SuppressWarnings("unchecked")
  private void moveUpNatural() {
    int k = size-1;
    Comparable<? super E> e = (Comparable<? super E>) H[k];
    while(k>0) {
      int up = (k-1) >>> 1;
      E upe  = H[up];
      if(e.compareTo((E) upe) >= 0)
        break;      // k ju� okre�la pozycj� docelow� e 
      H[k] = upe;
      k = up;       // W g�r� o jeden poziom
    }
    H[k] = (E)e;
  }

  //@SuppressWarnings("unchecked")
  private void moveUpCmp() {
    int k = size-1;
    E e = H[k];
    while(k>0) {
      int up = (k-1) >>> 1;
      E upe = H[up];
      if(cmp.compare(e, upe) >= 0)
        break;
      H[k] = upe;
      k = up;
    }
    H[k] = e;
  }

  //private void moveDown(int k, E x) {
  private void moveDown(int k) {
    if(cmp != null)
      moveDownCmp(k);
    else
      moveDownNatural(k);
  }

  @SuppressWarnings("unchecked")  
  private void moveDownNatural(int k) {
    // Wymuszenie możliwości stosowania compareTo()
    // do elementu H[k] "przetaczanego" w d�
    Comparable<? super E> e = (Comparable<? super E>)H[k];    
     
    int j=2*k+1;  // Indeks lewego potomka
    while(j<size) {
      if(j<size-1 &&
         ((Comparable<? super E>)H[j]).compareTo(H[j+1])>0)
        ++j;      // j: indeks mniejszego potomka
      if(e.compareTo((H[j]))<=0)
        break;     // Ju� OK.
      H[k] = H[j];
      k = j;       // W d� do nast�pnego poziomu
      j = 2*k+1;
    }
    H[k] = (E)e;  // Osadzenie obiektu w odpowiednim miejscu
  }
  
  private void moveDownCmp(int k) {
    E e = H[k];  // Element "przetaczany" w d�    
    int j=2*k+1;  // Indeks lewego potomka
    
    while(j<size) {
      if(j<size-1 &&
         cmp.compare(H[j], H[j+1])>0)
        ++j;      // j: indeks mniejszego potomka
      if(cmp.compare(e, H[j]) <=0 )
        break;     // Ju� OK.
      H[k] = H[j];
      k = j;       // W d� do nast�pnego poziomu
      j = 2*k+1;
    }
    H[k] = e;  // Osadzenie obiektu w odpowiednim miejscu
  }
  
  private void makeHeap() {
    for(int i = (size >>> 1) - 1; i >= 0; --i)
      moveDown(i);
  }

}

// Prosta definicja lokalna do celów ilustracyjnych.
// JDK zawiera klasy java.awt.Point, java.awt.geom.Point2D, ...
class Point implements Comparable<Point>{
  private int x, y; // Współrzdne kartezjańskie
  public Point() { this(0, 0); }
  public Point(int x, int y) { this.x=x; this.y=y; } 
  public Point(Point p) { this(p.x, p.y); } 

  @Override
  public boolean equals(Object o) { 
    if(!(o instanceof Point)) return false;
    Point p = (Point) o;
    return x==p.x && y==p.y; 
  }
  
  
  // Porządek "naturalny": współrz�dna x bardziej znacząca
  public int compareTo(Point p) {
    if(x<p.x) return -1;
    if(x>p.x) return 1;
    // x==p.x
    if(y<p.y) return -1;
    if(y>p.y) return 1;
    // x==px && y==p.y
    return 0;
  }
  
  int getX() { return x; }
  int getY() { return y; }
  
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}

// Przykładowe komparatory dla klasy Point
// -----------

// Współórzędna y bardziej znacząca w porównaniach
class PreferY implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    int x1 = p1.getX(),  y1 = p1.getY();
    int x2 = p2.getX(),  y2 = p2.getY();

    if(y1<y2) return -1;
    if(y1>y2) return 1;
    // y1 == y2
    if(x1<x2) return -1;
    if(x1>x2) return 1;
    // y1 == y2 && x1 == x2
    return 0;
  }
}

// Porównanie wg odległości od początku układu
// współrzędnych (odległość typu "Manhattan")
class OrtoDist implements Comparator<Point> {
  public int compare(Point p1, Point p2) {
    int d1 = Math.abs(p1.getX()) + Math.abs(p1.getY());
    int d2 = Math.abs(p2.getX()) + Math.abs(p2.getY());
    return d1-d2;
  }
}

// Odwrócenie porządku naturalnego
class Reverse implements Comparator<Point> {
  public int compare(Point p, Point q) {
    return q.compareTo(p);
  }
}
