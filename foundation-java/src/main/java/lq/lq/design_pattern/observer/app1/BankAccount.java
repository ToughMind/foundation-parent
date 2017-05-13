package lq.lq.design_pattern.observer.app1;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class BankAccount extends Observable {
	private String name;
	private double amt;
	List<BankObserver> list = new ArrayList<BankObserver>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmt() {
		return amt;
	}

	// 添加观察者
	public void add(BankObserver bankObserver) {
		list.add(bankObserver);
	}

	// 删除观察者
	public void remove(BankObserver bankObserver) {
		list.remove(bankObserver);
	}

	// 通知金额改变
	public void notifyAmt() {
		for (int i = 0; i < list.size(); i++) {
			BankObserver bankObserver = (BankObserver) list.get(i);
			bankObserver.update(amt);
		}
	}

	public void setAmt(double amt) {
		this.amt = amt;
		// notifyAmt();
		setChanged();
		notifyObservers(amt);
	}

}
