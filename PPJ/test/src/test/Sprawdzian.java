package test;
public class Sprawdzian {
  public static void main(String[] a) {
    System.out.println(g(5)); 
  }
  static int g(int n) {
    if(n==0) return n;
    return n - g(n-1);
  }
}