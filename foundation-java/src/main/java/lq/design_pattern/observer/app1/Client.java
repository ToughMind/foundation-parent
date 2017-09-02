package lq.design_pattern.observer.app1;

public class Client {
	public static void main(String[] argv) {
		BankAccount bankAccount = new BankAccount();
		CustomerBank1 customerBank1 = new CustomerBank1();
		customerBank1.setEmail("****@***.com");
		CustomerBank2 customerBank2 = new CustomerBank2();
		customerBank2.setPhone("123456789");
		CustomerBank3 customerBank3 = new CustomerBank3();
		customerBank3.setQq("000000000"); 
		bankAccount.addObserver(customerBank1);
		bankAccount.addObserver(customerBank2);
		bankAccount.addObserver(customerBank3);
		bankAccount.setAmt(1000);
	}
}
