package pl.edu.pw.ii.pte.junit.sorterPostCondition;

public class FirstItemFillingSort extends AbstractSort {

	public void sort(int tab[], int max) {
		for (int k = 0; k <= max; k++) {
			tab[k] = tab[0]; // filled with the first element
		}
	}
}
