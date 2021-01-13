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
		m12CHF = new Money(12_0000, "CHF"); // 12 CHF
		m14CHF = new Money(14_0000, "CHF"); // 14 CHF
		m10PLN = new Money(10_0000, "PLN"); // 10 PLN
		m5EUR = new Money(5_0000, "EUR"); // 5 EUR
		m20USD = new Money(20_0000, "USD"); // 20 USD
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleAdd() {
		Money expected = new Money(26_0000L, "CHF"); //26 CHF
		Money result = m12CHF.add(m14CHF); 
		assertTrue(expected.equals(result)); 
	}
	
	@Test
	public void testSimpleAddWhenMismachedCurrency() {
		Money expected = new Money(21_4083L, "EUR"); // 21.4083 EUR
		Money result = m5EUR.add(m20USD); 
		assertTrue(expected.equals(result)); 
	}

	@Test
	public void testEquals() {
		assertTrue(!m12CHF.equals(null));
		assertEquals(m12CHF, m12CHF);
		assertEquals(m12CHF, new Money(12_0000L, "CHF"));
		assertTrue(!m12CHF.equals(m14CHF));
	}
	
	@Test
	public void testEqualsValueOfDifferentCurrencies() {
		assertEquals(m12CHF, new Money(50_2260, "PLN"));
		assertTrue(!m12CHF.equals(m5EUR));
	}
	
	@Test
	public void testMultiply() {
		Money expected = new Money(24_0000L, "CHF");
		assertEquals(m12CHF.multiplyCurrency(2), expected);
		assertEquals(m12CHF.multiplyCurrency(0), new Money(0L, "CHF"));
		assertTrue(!m12CHF.multiplyCurrency(3).equals(expected));
		
	}
	
	@Test
	public void testAnyCurrencyAdd() {
		Money expected = new Money(32_6360L, "PLN"); //32,6360 PLN
		Money result = m10PLN.addAnyCurrency(m5EUR);
		assertTrue(expected.equals(result)); 
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(m10PLN.compareTo(new Money(10_0000, "PLN")), 0);
		assertEquals(m10PLN.compareTo(new Money(20_0000, "PLN")), 1);
		assertEquals(m10PLN.compareTo(new Money(1_0000, "PLN")), -1);
		assertEquals(m10PLN.compareTo(new Money(0, "PLN")), -1);
		assertEquals(m10PLN.compareTo(new Money(-10, "PLN")), -1);
	}
	
	@Test
	public void testCompareToMixedCurrencies() {
		assertEquals(m5EUR.compareTo(new Money(22_6360, "PLN")), 0);
		assertEquals(m5EUR.compareTo(m20USD), 1);
		assertEquals(m5EUR.compareTo(new Money(1_0000, "USD")), -1);
		assertEquals(m5EUR.compareTo(new Money(0, "USD")), -1);
		assertEquals(m5EUR.compareTo(new Money(-10, "PLN")), -1);
	}

}
