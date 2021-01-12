package pl.edu.pw.ii.pte.junit.sorterPostCondition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class TestSortClass2 {

	final int NMAX = 4;
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

	// PostCondition2 Table includes only elements from the input table
	@Test
	public void postCondition2() {
		s.fill_in(inputdata, NMAX);
		int[] copydata = Arrays.copyOf(inputdata, inputdata.length);
		
		s.sort(inputdata, NMAX);

		for (int k = 0; k <= NMAX; k++) {
			boolean flag = true;
			for (int j = 0; j <= NMAX; j++) {
				if (inputdata[k] == copydata[j]) {
					flag = false;
					break;
				}
			}
			assertFalse(flag,"element not from input table");
		}
	}

	public static class NoOpSortTest extends TestSortClass2 {
		@Override
		protected AbstractSort createInstance() {
			return new NoOpSort();
		}
	}
	
	public static class AscendingFillingSortTest extends TestSortClass2 {
		@Override
		protected AbstractSort createInstance() {
			return new AscendingFillingSort();
		}
	}

	public static class FirstItemFillingSortTest extends TestSortClass2 {
		@Override
		protected AbstractSort createInstance() {
			return new FirstItemFillingSort();
		}
	}
	
	public static class RealSortTest extends TestSortClass2 {
		@Override
		protected AbstractSort createInstance() {
			return new RealSort();
		}
	}
}
