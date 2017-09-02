package lq.design_pattern.proxy.aop;


import lq.design_pattern.proxy.TaxInterface;
import lq.design_pattern.proxy.Salary;
import lq.design_pattern.proxy.SalaryInterface;
import lq.design_pattern.proxy.Tax;

public class Client {
	
	public static void main (String[] args) { 
		MyProxy timeProxy = new MyProxy();
		SalaryInterface salaryInterface = (SalaryInterface)timeProxy.bind(new Salary(), new ControlAdvice());
		salaryInterface.doSalary();
		TaxInterface taxInterface = (TaxInterface)timeProxy.bind(new Tax(), new ControlAdvice());
		taxInterface.doTax();
    }
}
