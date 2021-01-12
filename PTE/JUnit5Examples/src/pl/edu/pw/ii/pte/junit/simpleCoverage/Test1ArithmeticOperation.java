package pl.edu.pw.ii.pte.junit.simpleCoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class Test1ArithmeticOperation {
	@Test
	public void multiply_zero() {
		ArithmeticOperation a = new ArithmeticOperation();
		assertEquals(0 * 0, a.multiply(0, 0));
	}

}
