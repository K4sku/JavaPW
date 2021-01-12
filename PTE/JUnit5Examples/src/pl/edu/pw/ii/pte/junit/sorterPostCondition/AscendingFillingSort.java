package pl.edu.pw.ii.pte.junit.sorterPostCondition;

public class AscendingFillingSort extends AbstractSort {

	public void sort(int tab[], int max) {
		for (int k = 0; k <= max; k++) {
			tab[k] = k; // tab filled with ascending numbers
		}
	}

}
