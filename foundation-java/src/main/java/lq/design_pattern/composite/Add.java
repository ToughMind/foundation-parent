package lq.design_pattern.composite;

public class Add implements SalaryComputer {

	@Override
	public double computer(double m, double n) {
		return m + n;
	}

}
