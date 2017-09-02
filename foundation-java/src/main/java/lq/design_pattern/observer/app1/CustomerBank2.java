package lq.design_pattern.observer.app1;

import java.util.Observable;
import java.util.Observer;

public class CustomerBank2 implements Observer {
	private String phone;

	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public void update(Observable  obj,  Object  arg) {
		double amt = Double.parseDouble(arg.toString());
		System.out.println("账户金额发生变动，发送短信到" + phone + ",变动后金额为：" + amt);
	}


}
