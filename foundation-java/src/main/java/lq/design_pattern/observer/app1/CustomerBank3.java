package lq.design_pattern.observer.app1;

import java.util.Observable;
import java.util.Observer;

public class CustomerBank3 implements Observer {
	private String qq;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
	public void update(Observable obj, Object arg) {
		double amt = Double.parseDouble(arg.toString());
		System.out.println("账户金额发生变动，发送信息到" + qq + ",变动后金额为：" + amt);
	}

}
