package pl.edu.pw.ii.pte.junit.executionOrder;

//JUnit5.4 or above required to run this example

import org.junit.jupiter.api.Test;
/*
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
*/
//@TestMethodOrder(Alphanumeric.class)
public class TestMethodOrder {

	@Test
	public void testC() {
		System.out.println("trzy");
	}

	@Test
	public void testA() {
		System.out.println("jeden");
	}

	@Test
	public void testB() {
		System.out.println("dwa");
	}
}
