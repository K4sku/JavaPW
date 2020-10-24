// Przekazywanie parametrów przez wartość
import java.util.*;

public class PPJ2_SwapNieDziala {
  public static void main(String[] args) {  
    int x1 = 111, x2 = 999;
    Object o1 = new Object(), o2 = new Object();
    
    System.out.println("Przed swap:    x1=" + x1 + " x2=" + x2);
    System.out.println("Przed swap:    o1=" + o1 + " o2=" + o2);
    
    swap(x1, x2);  
    swap(o1, o2);
    
    System.out.println("Po swap:       x1=" + x1 + " x2=" + x2);
    System.out.println("Po swap:       o1=" + o1 + " o2=" + o2);
    
    int[] A = { 1,2,3,4,5 };
    opA(A);
    
    // Wydruk bez opakowania [ ... ]
    for(int e: A) System.out.print(e+ " ");
    System.out.println();
    System.out.println("\nKoniec programu");
  }
  
  public static void opA(int[] A) {
    // Iteracja "for each": tablica A bez zmian
  	for(int e: A) e = e*e;  
    System.out.println(Arrays.toString(A));
    
    // Klasyczna iteracja for: modyfikacja tablicy 
    for(int i=0; i<A.length; ++i)
      A[i] *=A[i];
    System.out.println(Arrays.toString(A));
  }
  
  static void swap(int a, int b) {
    int tmp = a; a = b; b = tmp;
    System.out.println("Wewnątrz swap: a=" + a + " b=" + b);
  }
  
  static void swap(Object a, Object b) {
    Object tmp = a; a = b; b = tmp;
    System.out.println("Wewnątrz swap: a=" + a + " b=" + b);
  }
  
}