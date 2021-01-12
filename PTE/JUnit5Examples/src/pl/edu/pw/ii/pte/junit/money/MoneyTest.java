package pl.edu.pw.ii.pte.junit.money;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MoneyTest {

	@Test
	public void testSimpleAdd() {
		Money m12CHF = new Money(12, "CHF"); //
		Money m14CHF = new Money(14, "CHF");
		Money expected = new Money(26, "CHF");
		Money result = m12CHF.add(m14CHF); //
		assertTrue(expected.equals(result)); //
		}

	@Test
	public void testEquals() {
		Money m12CHF = new Money(12, "CHF");
		Money m14CHF = new Money(14, "CHF");
		Money m12PLN = new Money(12, "PNL");

		assertTrue(!m12CHF.equals(null));
		assertEquals(m12CHF, m12CHF);
		assertEquals(m12CHF, new Money(12, "CHF"));
		assertTrue(!m12CHF.equals(m14CHF));
		assertTrue(!m12CHF.equals(m12PLN));
		}

}