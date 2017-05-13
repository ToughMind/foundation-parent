package lq.lq.design_pattern.template;

public class Client {
	public static void main (String[] args) {
		Salary salary = new Salary(new SaleSalary());
		salary.doSalary();
    }
}
