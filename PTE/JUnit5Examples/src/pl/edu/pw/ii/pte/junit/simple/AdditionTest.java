package pl.edu.pw.ii.pte.junit.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AdditionTest {
	private int x = 2;
	private int y = 3;

	@Test
	public void addition() {
		int z = x + y;
		assertEquals(5, z);
	}

}
