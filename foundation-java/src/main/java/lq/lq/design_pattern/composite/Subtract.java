package lq.lq.design_pattern.composite;

public class Subtract implements SalaryComputer {
	@Override
	public double computer(double m, double n) {
		return m - n;
	}
}
