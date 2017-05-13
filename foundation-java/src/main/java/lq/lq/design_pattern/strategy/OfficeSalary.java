package lq.lq.design_pattern.strategy;

public class OfficeSalary implements SalaryComputer {

	@Override
	public void doSalary() {
		System.out.println("对于行政人员，每个月只有基本工资");
	}

}
