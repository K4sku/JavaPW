import java.util.Scanner;

public class Sprawdzian {
	public static void main(String[] args) {
		Integer[] itab = {1,2,3,4,5};

		for(Integer e: itab) e *= e;
		for(int i=0; i<itab.length; ++i)
		  System.out.print(itab[i] +" ");
}
}