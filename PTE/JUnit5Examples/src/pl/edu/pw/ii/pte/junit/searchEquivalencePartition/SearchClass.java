package pl.edu.pw.ii.pte.junit.searchEquivalencePartition;

import java.util.*;

public class SearchClass {

	public void fill_in(int tab[]) {
		Random randomGenerator = new Random();
		for (int k = 0; k < tab.length; k++) {
			tab[k] = randomGenerator.nextInt(100 * tab.length);
		}
	}

	public void print(int tab[]) {
		for (int k = 0; k < tab.length; k++) {
			System.out.println(tab[k]);
		}
	}

	public int search(int tab[], int key) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == key)
				return i;
		}
		return -1;
	}

}
