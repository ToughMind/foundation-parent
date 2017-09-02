package lq.design_pattern.strategy;

public class SaleSalary implements SalaryComputer {

	@Override
	public void doSalary() {
		System.out.println("对于销售人员每个月根据销售业绩来确定工资");
	}

}