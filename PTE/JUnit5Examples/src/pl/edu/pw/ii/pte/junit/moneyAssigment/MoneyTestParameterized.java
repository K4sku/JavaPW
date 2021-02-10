package pl.edu.pw.ii.pte.junit.moneyAssigment;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTestParameterized {
	@ParameterizedTest
	@ValueSource(strings = {"PLN", "USD", "EUR", "CHF"})
	public void testSimpleAdd(String currency) {
		Money m12curr = new Money(12_0000L, currency);
		Money m14curr = new Money(14_0000L, currency);
		Money expected = new Money(26_0000L, currency);
		Money result = m12curr.add(m14curr); 
		assertTrue(expected.equals(result)); 
	}
	
	@ParameterizedTest
	@CsvSource({
		"0, PLN, 0, USD, 0",
		"0, PLN, 10000, EUR, 45272",
		"1000000, CHF, 0, PLN, 1000000",
		"50000, EUR, 200000, USD, 214083",
		"200000, USD, 50000, EUR, 260944",
		"10000, USD, 10000, PLN, 12692"
	})
	public void testSimpleAddWhenMismachedCurrency(
			Long aVal, String aCurr, Long bVal, String bCurr, Long expectedVal
			) {
		Money a = new Money(aVal, aCurr);
		Money b = new Money(bVal, bCurr);
		Money expected = new Money(expectedVal, aCurr);
		Money result = a.add(b); 
		assertTrue(expected.equals(result)); 
	}

	@ParameterizedTest
	@CsvSource({
		"0, PLN, 10000, EUR",
		"1000000, CHF, 0, PLN",
		"50000, EUR, 200000, USD",
		"200000, USD, 50000, EUR",
		"10000, USD, 10000, PLN"
	})
	public void testEquals(
			Long aVal, String aCurr, Long bVal, String bCurr
			) {
		Money a = new Money(aVal, aCurr);
		Money b = new Money(bVal, bCurr);
		Money oa = new Money(0L, aCurr);
		Money ob = new Money(0L, bCurr);
		
		assertFalse(a.equals(null));
		assertEquals(a, a);
		assertEquals(oa, ob);
		assertEquals(b, new Money(bVal, bCurr));
		assertFalse(a.equals(b));
	}
	
	@ParameterizedTest
	@CsvSource({
		"0, PLN, 0, EUR",
		"1000000, CHF, 4185500, PLN",
		"50000, EUR, 60944, USD",
		"200000, USD, 164083, EUR",
		"10000, USD, 37142, PLN"
	})
	public void testEqualsValueOfDifferentCurrencies(
			Long aVal, String aCurr, Long bVal, String bCurr
			) {
		Money a = new Money(aVal, aCurr);
		Money b = new Money(bVal, bCurr);
		Money c = new Money(-10, "PLN");
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
	}
	
	@ParameterizedTest
	@CsvSource({
		"1000000, CHF, 2000000",
		"50000, EUR, 100000"
	})
	public void testMultiply(Long aVal, String aCurr, Long expectedVal) {
		Money a = new Money(aVal, aCurr);
		Money expected = new Money(expectedVal, aCurr);
		assertEquals(a.multiplyCurrency(0), new Money(0L, aCurr));
		assertEquals(a.multiplyCurrency(2), expected);
		assertFalse(a.multiplyCurrency(3).equals(expected));
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"0, PLN, 0, USD, 0",
		"0, PLN, 10000, EUR, 45272",
		"1000000, CHF, 0, PLN, 1000000",
		"50000, EUR, 200000, USD, 214083",
		"200000, USD, 50000, EUR, 260944",
		"10000, USD, 10000, PLN, 12692"
	})
	public void testAnyCurrencyAdd(
			Long aVal, String aCurr, Long bVal, String bCurr, Long expectedVal
			) {
		Money a = new Money(aVal, aCurr);
		Money b = new Money(bVal, bCurr);
		Money expected = new Money(expectedVal, aCurr);
		Money result = a.addAnyCurrency(b); 
		assertTrue(expected.equals(result)); 
	}
	
	@ParameterizedTest
	@CsvSource({
		"10000, 20000, PLN",
		"20000, 40000, USD",
		"30000, 40000, EUR",
		"0, 10000, CHF"
	})
	public void testCompareTo(
			Long lowerVal, Long higherVal, String Curr
			) {
		Money lower = new Money(lowerVal, Curr);
		Money higher = new Money(higherVal, Curr);

		assertEquals(higher.compareTo(higher), 0);
		assertTrue(higher.equals(higher));
		assertEquals(higher.compareTo(lower), 1);
		assertEquals(lower.compareTo(higher), -1);
	
		assertEquals(-(higher.compareTo(lower)), lower.compareTo(higher));
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"0, PLN, 10000, EUR",
		"10000, CHF, 10000000000, PLN",
		"50000, EUR, 200000, USD",
		"10000, USD, 5000000, CHF"
	})
	public void testCompareToMixedCurrencies(
			Long lowerVal, String lowerCurr, Long higherVal, String higherCurr
			) {
		Money lower = new Money(lowerVal, higherCurr);
		Money higher = new Money(higherVal, lowerCurr);

		assertEquals(new Money(0, higherCurr).compareTo(new Money(0, lowerCurr)), 0);
		assertEquals(higher.compareTo(lower), 1);
		assertEquals(lower.compareTo(higher), -1);
		
	}

}
