package lq.lq.design_pattern.template;

public class Salary {
	private SalaryComputer salaryComputer;

	public Salary(SalaryComputer salaryComputer) {
		this.salaryComputer = salaryComputer;
	}

	public void doSalary() {
		salaryComputer.doSalary();
	}
}
