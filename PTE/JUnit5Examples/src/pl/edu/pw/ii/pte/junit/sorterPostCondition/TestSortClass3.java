package pl.edu.pw.ii.pte.junit.sorterPostCondition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class TestSortClass3 {

	final int NMAX = 3;
	int[] inputdata = new int[NMAX + 1];
	AbstractSort s;

	protected abstract AbstractSort createInstance();

	@BeforeEach
	public void setUp() {
		s = createInstance();
	}

	// PostCondition1 Table is sorted
	@Test
	public void postCondition1() {
		s.fill_in(inputdata, NMAX);

		s.sort(inputdata, NMAX);

		for (int k = 0; k < NMAX; k++) {
			assertTrue(inputdata[k] <= inputdata[k + 1],"table not sorted");
		}
	}

	// PostCondition3 Output table is permutation of elements from the input table
	@Test
	public void postCondition3() {
		s.fill_in(inputdata, NMAX);
		int[] copydata = Arrays.copyOf(inputdata, inputdata.length);

		s.sort(inputdata, NMAX);

		int i = 0;
		for (int k = 0; k <= NMAX; k++) {
			boolean flag = true;
			for (int j = 0; j <= (NMAX - i); j++) {
				if (inputdata[k] == copydata[j]) {
					flag = false;
					if (NMAX - j > 0)
						System.arraycopy(copydata, j + 1, copydata, j, NMAX - j);
					i++;
					break;
				}
			}
			assertFalse(flag,"element not found");

		}
		assertTrue(i == (NMAX + 1),"not all elements included in output table");
	}

	public static class NoOpSortTest extends TestSortClass3 {
		@Override
		protected AbstractSort createInstance() {
			return new NoOpSort();
		}
	}
	
	public static class AscendingFillingSortTest extends TestSortClass3 {
		@Override
		protected AbstractSort createInstance() {
			return new AscendingFillingSort();
		}
	}
	
	public static class FirstItemFillingSortTest extends TestSortClass3 {
		@Override
		protected AbstractSort createInstance() {
			return new FirstItemFillingSort();
		}
	}
	
	public static class RealSortTest extends TestSortClass3 {
		@Override
		protected AbstractSort createInstance() {
			return new RealSort();
		}
	}
}
