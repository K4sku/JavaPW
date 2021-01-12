package pl.edu.pw.ii.pte.junit.executionOrder;

//JUnit5.4 or above required to run this example

import org.junit.jupiter.api.Test;
/* 
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
*/

//@TestMethodOrder(OrderAnnotation.class)
public class OrderAnnotationMethodOrder {
	@Test
//	@Order(3)
	public void testC() {
		System.out.println("trzy");
	}

	@Test
//	@Order(2)
	public void testA() {
		System.out.println("jeden");
	}

	@Test
//	@Order(1)
	public void testB() {
		System.out.println("dwa");
	}

}
