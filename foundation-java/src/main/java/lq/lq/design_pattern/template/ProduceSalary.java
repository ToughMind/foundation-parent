package lq.lq.design_pattern.template;

public class ProduceSalary extends SalaryComputer {
	@Override
	public void doBase() {
		System.out.println("对于生产人员，计算基本工资");
	}

	@Override
	public void doBonus() {
		System.out.println("对于生产人员，计算奖金");
	}

	@Override
	public void doTax() {
		System.out.println("对于生产人员，计算所得税");
	}

}
