package pl.edu.pw.ii.pte.junit.inheritanceAssigment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagerTestAssigment {
	Manager zeroMan;
	Manager man;

	@BeforeEach
	public void setUp() {
		zeroMan = new Manager("", 0, 0, 0, 0);
		man = new Manager("John Smith", 100000, 2019, 12, 30);
	
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetName() {
		assertEquals("", zeroMan.getName());
		assertEquals("John Smith", man.getName());
	}
	
	@Test
	public void testGetHireDay() {
		GregorianCalendar calendar = new GregorianCalendar(0, 0 - 1, 0);
		Date hireDay = calendar.getTime();
		assertEquals(hireDay, zeroMan.getHireDay());
			
		calendar = new GregorianCalendar(2019, 12-1, 30);
		hireDay = calendar.getTime();	
		assertEquals(hireDay, man.getHireDay());
	}
	
	@Test
	public void testGetSalary()	{
		assertEquals(0, zeroMan.getSalary());
		assertEquals(100000, man.getSalary());
	}
	
	@Test
	public void testSetBonus() {
		zeroMan.setBonus(0);
		assertEquals(0, zeroMan.getSalary());
		zeroMan.setBonus(100);
		assertEquals(100, zeroMan.getSalary());
		
		man.setBonus(0);
		assertEquals(100000, man.getSalary());
		man.setBonus(1000);
		assertEquals(101000, man.getSalary());
	}
	
	@Test
	public void testSetBonus2() {
		zeroMan.setBonus(-100);
		assertEquals(-100, zeroMan.getSalary());
		
		man.setBonus(-1000);
		assertEquals(99000, man.getSalary());
	}
	
	@Test
	public void testRaiseSalary() {      
		zeroMan.raiseSalary(0);
		assertEquals(0, zeroMan.getSalary());
		
		man.raiseSalary(0);
		assertEquals(100000, man.getSalary());
	}
	
	@Test
	public void testRaiseSalary2() {      
		zeroMan.raiseSalary(0.1);
		assertEquals(0, zeroMan.getSalary());
		
		man.raiseSalary(0.1);
		assertEquals(100100, man.getSalary());
	}
	
	@Test
	public void testRaiseSalary3() {      
		zeroMan.raiseSalary(10);
		assertEquals(0, zeroMan.getSalary());
		
		man.raiseSalary(10);
		assertEquals(110000, man.getSalary());
	}
	
	@Test
	public void testRaiseSalary4() {
		zeroMan.raiseSalary(-10);
		assertEquals(0, zeroMan.getSalary());
		
		man.raiseSalary(-10);
		assertEquals(90000, man.getSalary());
	}
	
}
