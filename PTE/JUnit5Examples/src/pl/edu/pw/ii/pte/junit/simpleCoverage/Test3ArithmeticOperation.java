package pl.edu.pw.ii.pte.junit.simpleCoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class Test3ArithmeticOperation {

	@Test
	public void multiplyCorrect_zero() {
		ArithmeticOperation a = new ArithmeticOperation();
		assertEquals(0 * 0, a.multiplyCorrect(0, 0));
	}

	@Test
	public void multiplyCorrect_three() {
		ArithmeticOperation a = new ArithmeticOperation();
		assertEquals(3 * 3, a.multiplyCorrect(3, 3));
	}

}
