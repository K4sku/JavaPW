package pl.edu.pw.ii.pte.patterns.account_with_state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	Account a;
	Account b;
	
	@BeforeEach
	public void setUp() {
		a = new Account("Ann", 0);
		b = new Account("Betty", 10);
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(0, a.getBalance());
		assertEquals(10, b.getBalance());
	}
	
	@Test
	public void testCredit() {
		a.credit(100);
		assertEquals(100, a.getBalance());
		a.credit(100);
		assertEquals(200, a.getBalance());
	}
	
	@Test
	public void testCreditOnSuspendedAccount() {
		a.suspend();
		a.credit(100);
		assertEquals(10, a.getBalance());
		a.credit(100);
		assertEquals(20, a.getBalance());
	}
	
	@Test
	public void creditThrowsWhenTryingToCreditOnClosedAccount() {
		a.close();
		assertThrows(
				IllegalStateException.class,()->{
				a.credit(1000);
			}
		);
	}
	
	@Test
	public void testGetOwner() {
		assertEquals("Ann", a.getOwner());
		assertEquals("Betty", b.getOwner());
	}

}
