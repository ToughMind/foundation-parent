package lq.lq.design_pattern.strategy;

public class Salary {
	private SalaryComputer salaryComputer;

	public Salary(SalaryComputer salaryComputer) {
		this.salaryComputer = salaryComputer;
	}

	public void doSalary() {
		salaryComputer.doSalary();
	}

}
