package lq.lq.design_pattern.strategy;

public class Client {
	public static void main(String[] args) { 
		Salary salary = new Salary(new SaleSalary());
		salary.doSalary();
	}
}
