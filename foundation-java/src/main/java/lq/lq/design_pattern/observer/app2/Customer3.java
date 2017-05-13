package lq.lq.design_pattern.observer.app2;

import java.util.Observable;
import java.util.Observer;

public class Customer3 implements Observer {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void update(Observable obj, Object arg) {
		double price = Double.parseDouble(arg.toString());
		System.out.println("该货物降价了，快来购买吧，发送邮件到" + email + ",变动后价格为：" + price);
	}
}
