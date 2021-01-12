package pl.edu.pw.ii.pte.junit.sorterPostCondition;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public abstract class TestSortClass1 {
	final int NMAX = 4;
	int[] inputdata = new int[NMAX + 1];

	protected abstract AbstractSort createInstance();

	// PostCondition1 Table is sorted
	@Test
	public void postCondition1() {
		AbstractSort s = createInstance();
		s.fill_in(inputdata, NMAX);

		s.sort(inputdata, NMAX);

		for (int k = 0; k < NMAX; k++) {
			assertTrue(inputdata[k] <= inputdata[k + 1], "table not sorted");
		}
	}

	public static class NoOpSortTest extends TestSortClass1 {
		@Override
		protected AbstractSort createInstance() {
			return new NoOpSort();
		}
	}
	
	public static class AscendingFillingSortTest extends TestSortClass1 {
		@Override
		protected AbstractSort createInstance() {
			return new AscendingFillingSort();
		}
	}
	
	public static class FirstItemFillingSortTest extends TestSortClass1 {
		@Override
		protected AbstractSort createInstance() {
			return new FirstItemFillingSort();
		}
	}
	
	public static class RealSortTest extends TestSortClass1 {
		@Override
		protected AbstractSort createInstance() {
			return new RealSort();
		}
	}

}
