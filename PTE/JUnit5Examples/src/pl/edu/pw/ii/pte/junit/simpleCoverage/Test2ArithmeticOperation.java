package pl.edu.pw.ii.pte.junit.simpleCoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class Test2ArithmeticOperation {
	@Test
	public void multiply_zero() {
		ArithmeticOperation a = new ArithmeticOperation();
		assertEquals(0 * 0, a.multiply(0, 0));
	}

	@Test
	public void multiply_three() {
		ArithmeticOperation a = new ArithmeticOperation();
		assertEquals(3 * 3, a.multiply(3, 3));
	}

}
