package pl.edu.pw.ii.pte.junit.moneyAssigment;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyTest2 {
	private Money m12CHF;
	private Money m14CHF;
	private Money m10PLN;
	private Money m5EUR;
	private Money m20USD;

	@BeforeEach
	public void setUp() throws Exception {
		m12CHF = new Money(120000, "CHF");
		m14CHF = new Money(140000, "CHF");
		m10PLN = new Money(100000, "PLN");
		m5EUR = new Money(50000, "EUR");
		m20USD = new Money(200000, "USD");
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleAdd2() {
		Money expected = new Money(260000, "CHF");
		Money result = m12CHF.add(m14CHF); //
		assertTrue(expected.equals(result)); //
	}

	@Test
	public void testEquals2() {
		assertTrue(!m12CHF.equals(null));
		assertEquals(m12CHF, m12CHF);
		assertEquals(m12CHF, new Money(120000, "CHF"));
		assertTrue(!m12CHF.equals(m14CHF));
	}
	
	@Test
	public void testMultiply() {
		Money expected = new Money(240000, "CHF");
		assertEquals(m12CHF.multiplyCurrency(2), expected);
		assertEquals(m12CHF.multiplyCurrency(0), new Money(0, "CHF"));
		assertTrue(!m12CHF.multiplyCurrency(3).equals(expected));
		
	}
	
	@Test
	public void testAnyCurrencyAdd() {
		Money expected = new Money(2263610000, "PLN");
		Money result = m10PLN.addAnyCurrency(m5EUR);
		assertTrue(expected.equals(result)); //
	}

}
