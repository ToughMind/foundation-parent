package lq.design_pattern.observer.app2;

public class Client {
	public static void main(String[] argv) { 
		Product product = new Product();
		Customer1 customer1 = new Customer1();
		customer1.setEmail("****@***.com");
		Customer2 customer2 = new Customer2();
		customer2.setEmail("****@***.com");
		Customer3 customer3 = new Customer3();
		customer3.setEmail("****@***.com");
		product.addObserver(customer1);
		product.addObserver(customer2);
		product.addObserver(customer3);
		product.setPrice(1);
	}
}
