package pl.edu.pw.ii.pte.mocks.easymock_with_junit;

import java.util.List;

public class BonusDistributor {

	public void distribute(int bonus, List<Employee> employees) {
		int salarySum = getSalarySum(employees);
		for (Employee e : employees) {
			e.setBonus(bonus * e.getSalary() / salarySum);
		}
	}
	
	private int getSalarySum(List<Employee> employees) {
		int sum = 0;
		for (Employee e : employees) {
			sum += e.getSalary();
		}
		return sum;
	}
}
