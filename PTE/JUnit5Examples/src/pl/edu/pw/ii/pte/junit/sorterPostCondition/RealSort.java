package pl.edu.pw.ii.pte.junit.sorterPostCondition;

import java.util.Arrays;

public class RealSort extends AbstractSort {
	
	public void sort(int tab [], int max) {
		Arrays.sort(tab);  // sorting using built-in routine
	}
}
