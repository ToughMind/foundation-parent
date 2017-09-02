package lq.design_pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements SalaryComputer {
	private List<SalaryComputer> list = new ArrayList<SalaryComputer>();

	// 添加运算符
	public void add(SalaryComputer salaryComputer) {
		list.add(salaryComputer);
	}

	// 删除运算符
	public void remove(SalaryComputer salaryComputer) {
		list.remove(salaryComputer);
	}

	// 递归
	public double computer(double x, double y) {
		double count = 0d; 
		for (int i = 0; i < list.size(); i++) {
			SalaryComputer salaryComputer = (SalaryComputer) list.get(i);
			count += salaryComputer.computer(x, y);
		}
		System.out.println("x = " + x + ",y = " + y + "计算后的结果为：" + count);
		return count;
	}
}
