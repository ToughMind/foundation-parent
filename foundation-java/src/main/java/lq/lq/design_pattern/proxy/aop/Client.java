package lq.lq.design_pattern.proxy.aop;

import proxy.*;
 
public class Client {
	
	public static void main (String[] args) { 
		MyProxy timeProxy = new MyProxy();
		SalaryInterface salaryInterface = (SalaryInterface)timeProxy.bind(new Salary(), new ControlAdvice());
		salaryInterface.doSalary();
		TaxInterface taxInterface = (TaxInterface)timeProxy.bind(new Tax(), new ControlAdvice());
		taxInterface.doTax();
    }
}
