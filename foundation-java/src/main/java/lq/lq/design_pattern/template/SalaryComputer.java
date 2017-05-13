package lq.lq.design_pattern.template;

public abstract class SalaryComputer {
	public void doSalary(){
		doBase();
		doBonus();
		doTax();
	}
	public abstract void doBase();
	public abstract void doBonus();
	public abstract void doTax();
}
