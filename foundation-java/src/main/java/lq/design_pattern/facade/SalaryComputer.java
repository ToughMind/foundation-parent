package lq.design_pattern.facade;

public class SalaryComputer implements Computer{
	public double doSalary(String empno) {
		Salary salary = new Salary();
		Holiday holiday = new Holiday();
		Tax tax = new Tax();
		double money = salary.getSalary(empno) * holiday.getHoliday(empno) / 30 - tax.getTax(empno);
		return money;
	}
}
