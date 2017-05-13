package lq.lq.design_pattern.proxy;

public class Client {
	public static void main (String[] args) { 		
		TimeProxy timeProxy = new TimeProxy();
		SalaryInterface salaryInterface = (SalaryInterface)timeProxy.bind(new Salary());
		salaryInterface.doSalary();
		TaxInterface taxInterface = (TaxInterface)timeProxy.bind(new Tax());
		taxInterface.doTax();
    }
}
