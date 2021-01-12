package pl.edu.pw.ii.pte.junit.sorterPostCondition;

import java.util.*;

public abstract class AbstractSort {

	public void fill_in(int tab[], int max) {
		// example only, randomness is discouraged in unit testing
		Random randomGenerator = new Random();
		for (int k = 0; k <= max; k++) {
			tab[k] = randomGenerator.nextInt(2 * max);
		}
	}

	public void print(int tab[], int max) {
		for (int k = 0; k <= max; k++) {
			System.out.println(tab[k]);
		}
	}

	public abstract void sort(int tab[], int max);
}
