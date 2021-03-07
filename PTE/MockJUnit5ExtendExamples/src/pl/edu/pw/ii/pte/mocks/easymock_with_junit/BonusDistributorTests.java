package pl.edu.pw.ii.pte.mocks.easymock_with_junit;

import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class BonusDistributorTests extends EasyMockSupport {

	@TestSubject
	private BonusDistributor distributor = new BonusDistributor();

	@Mock
	private Employee employee1;

	@Mock
	private Employee employee2;

	@Test
	public void singleEmployeeGetsAllTheBonus() {
		List<Employee> employees = Arrays.asList(employee1);
		
		employee1.setBonus(1000);
		expect(employee1.getSalary()).andReturn(200).atLeastOnce();
		replayAll();
		
		distributor.distribute(1000, employees);

		verifyAll();
	}

	@Test
	public void eachOfTwoEmployeesWithSameSalaryGetsHalfOfBonus() {
		List<Employee> employees = Arrays.asList(employee1, employee2);
		
		employee1.setBonus(500);
		employee2.setBonus(500);
		expect(employee1.getSalary()).andReturn(200).atLeastOnce();
		expect(employee2.getSalary()).andReturn(200).atLeastOnce();
		replayAll();
		
		distributor.distribute(1000, employees);

		verifyAll();
	}
	
	@Test
	public void employeeWithHigherSalaryGetsHigherBonus() {
		List<Employee> employees = Arrays.asList(employee1, employee2);
		
		employee1.setBonus(200);
		employee2.setBonus(400);
		expect(employee1.getSalary()).andReturn(200).atLeastOnce();
		expect(employee2.getSalary()).andReturn(400).atLeastOnce();
		replayAll();
		
		distributor.distribute(600, employees);

		verifyAll();
	}
}
