package pl.edu.pw.ii.pte.junit.assertions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AssertTests {

	@Test
	public void testAssertEquals() {
		assertEquals("text", "text","failure - strings are not equal");
	}

	@Test
	public void testAssertEquals2() {
		assertEquals("text1", "text2","failure - strings are not equal");
	}

	@Test
	public void testAssertArrayEquals() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcd".getBytes();
		assertArrayEquals(expected, actual,"failure - byte arrays not same");
	}

	@Test
	public void testAssertArrayEquals2() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcdef".getBytes();
		assertArrayEquals(expected, actual,"failure - byte arrays not same");
	}

	@Test
	public void testAssertArrayEquals3() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "cdef".getBytes();
		assertArrayEquals(expected, actual,"failure - byte arrays not same");
	}

	@Test
	public void testAssertArrayEquals4() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcf".getBytes();
		assertArrayEquals(expected, actual,"failure - byte arrays not same");
	}

	@Test
	public void testAssertArraySame() {
		byte[] expected = "abcd".getBytes();
		assertSame(expected, expected);
	}

	@Test
	public void testAssertArraySame2() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcd".getBytes();
		assertSame(expected, actual);
	}

}
