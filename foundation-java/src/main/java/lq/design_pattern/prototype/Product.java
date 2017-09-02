package lq.design_pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Cloneable,Serializable{
	
	private String name;
	private double price;
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException { 
		Product obj = (Product)super.clone();
		obj.address = (Address) address.clone();
		return obj;
	} 
	
	public Object deepClone() throws IOException, ClassNotFoundException {  

	    /* 写入当前对象的二进制流 */  
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	    ObjectOutputStream oos = new ObjectOutputStream(bos);  
	    oos.writeObject(this);  

	    /* 读出二进制流产生的新对象 */  
	    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
	    ObjectInputStream ois = new ObjectInputStream(bis);  
	    return ois.readObject();  
	}	
	
}
