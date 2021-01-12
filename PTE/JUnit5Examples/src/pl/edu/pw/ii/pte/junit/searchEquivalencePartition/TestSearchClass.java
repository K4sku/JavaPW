package pl.edu.pw.ii.pte.junit.searchEquivalencePartition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;

//in comments variants with more random data

public class TestSearchClass {

	int key;
	SearchClass s = new SearchClass();
	Random randomGenerator = new Random();

	@Test
	public void emptyArray() {
		int[] inputdata = new int[0];
		key = 6;
		// key = randomGenerator.nextInt(1000);
		assertEquals(-1, s.search(inputdata, key),"empty array");
	}

	@Test
	public void oneElementKey() {
		int[] inputdata = new int[1];
		key = 5;
		inputdata[0] = key;
		assertEquals(0, s.search(inputdata, key));
	}

	@Test
	public void oneElementNoKey() {
		int[] inputdata = new int[1];
		key = 5;
		inputdata[0] = key + 100;
		assertEquals(-1, s.search(inputdata, key));
	}

	@Test
	public void manyElementsFirstKey() {
		int[] inputdata = new int[10];
		// int [] inputdata = new int [randomGenerator.nextInt(10)];
		s.fill_in(inputdata);
		key = inputdata[0];
		assertEquals(0, s.search(inputdata, key));
	}

	@Test
	public void manyElementsLastKey() {
		int[] inputdata = new int[10];
		// int [] inputdata = new int [randomGenerator.nextInt(10)];
		s.fill_in(inputdata);
		key = inputdata[inputdata.length - 1];
		assertEquals(inputdata.length - 1, s.search(inputdata, key));
	}

	@Test
	public void manyElementsMiddleKey() {
		int[] inputdata = new int[10];
		// int [] inputdata = new int [randomGenerator.nextInt(10)];
		s.fill_in(inputdata);
		key = inputdata[(inputdata.length - 1) / 2];
		assertEquals((inputdata.length - 1) / 2, s.search(inputdata, key));
	}

	@Test
	public void manyElementsNoKey() {
		final int NMAX = 10;
		int[] inputdata = new int[NMAX + 1];
		for (int i = 0; i <= NMAX; i++) {
			inputdata[i] = 1;
		}
		key = NMAX + 100;
		assertEquals(-1, s.search(inputdata, key));
	}

}
