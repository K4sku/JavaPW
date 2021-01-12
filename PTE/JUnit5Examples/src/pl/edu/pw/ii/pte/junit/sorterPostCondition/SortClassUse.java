package pl.edu.pw.ii.pte.junit.sorterPostCondition;

public class SortClassUse {
	static final int NMAX = 5;
	static int[] inputdata = new int[NMAX + 1];

	public static void main(String[] args) {
		AbstractSort s = new NoOpSort();

		s.fill_in(inputdata, NMAX);
		System.out.println("Before sort");
		s.print(inputdata, NMAX);

		s.sort(inputdata, NMAX);

		System.out.println("After sort");
		s.print(inputdata, NMAX);

	}

}
