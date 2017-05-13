package lq.lq.design_pattern.prototype;

import java.io.Serializable;

public class Address implements Cloneable,Serializable{
	
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { 		
		return super.clone();		
	}	
	
}
