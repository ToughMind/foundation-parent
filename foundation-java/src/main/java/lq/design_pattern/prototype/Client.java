package lq.design_pattern.prototype;

import java.io.IOException;

public class Client {
	
	public static void main (String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException { 
		Product product = new Product();
		Address address = new Address();
		address.setAddress("仓库1");
		product.setName("产品");
		product.setPrice(15);
		product.setAddress(address); 
		System.out.println("原来的对象：name = " + product.getName()  + ",Address = " + product.getAddress().getAddress() + ",Price = " + product.getPrice() );
	
		//Product product1 = (Product) product.clone();
		Product product1 = (Product) product.deepClone();
		System.out.println("新的对象：name = " + product1.getName()  + ",Address = " + product1.getAddress().getAddress() + ",Price = " + product1.getPrice() );
		product1.setName("新产品");
		product1.getAddress().setAddress("仓库2"); 
		System.out.println("修改了name和address的原对象：name = " + product.getName()  + ",Address = " + product.getAddress().getAddress() + ",Price = " + product.getPrice() );
		System.out.println("修改了name和address的新对象：name = " + product1.getName()  + ",Address = " + product1.getAddress().getAddress() + ",Price = " + product1.getPrice() );
	}
}
