package lq.lq.design_pattern.observer.app1;

import java.util.Observable;
import java.util.Observer;
 
public class CustomerBank1 implements Observer {
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public void update(Observable  obj,  Object  arg) {
		double amt = Double.parseDouble(arg.toString());
		System.out.println("账户金额发生变动，发送邮件到" + email + ",变动后金额为：" + amt);
	}
}
