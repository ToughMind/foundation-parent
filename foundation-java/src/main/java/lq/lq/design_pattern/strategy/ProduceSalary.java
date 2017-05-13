package lq.lq.design_pattern.strategy;

public class ProduceSalary implements SalaryComputer {

	@Override
	public void doSalary() {
		System.out.println("对于生产人员则根据每个月的产量来确定工资");
	}

}
