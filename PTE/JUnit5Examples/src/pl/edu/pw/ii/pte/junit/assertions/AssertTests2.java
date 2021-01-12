package pl.edu.pw.ii.pte.junit.assertions;

import org.junit.Test;
import org.junit.Assert;

public class AssertTests2 {
	@Test
	public void testAssertEquals() {
		Assert.assertEquals("failure - strings are not equal", "text", "text");
	}

	@Test
	public void testAssertEquals2() {
		Assert.assertEquals("failure - strings are not equal", "text1", "text2");
	}

	@Test
	public void testAssertArrayEquals() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcd".getBytes();
		Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	public void testAssertArrayEquals2() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcdef".getBytes();
		Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	public void testAssertArrayEquals3() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "cdef".getBytes();
		Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	public void testAssertArrayEquals4() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcf".getBytes();
		Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	public void testAssertArraySame() {
		byte[] expected = "abcd".getBytes();
		Assert.assertSame(expected, expected);
	}

	@Test
	public void testAssertArraySame2() {
		byte[] expected = "abcd".getBytes();
		byte[] actual = "abcd".getBytes();
		Assert.assertSame(expected, actual);
	}

}
