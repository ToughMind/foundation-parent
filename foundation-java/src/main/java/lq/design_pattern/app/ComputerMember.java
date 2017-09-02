package lq.design_pattern.app;

import java.util.Iterator;
import java.util.List;

public class ComputerMember {
	private double total;

	public double getTotal() {
		return total;
	}

	public void account(List<?> list) {
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			Visitor visitor = new Visitor();
			System.out.println("该会员总价：" + ((Member) object).accept(visitor));
		}
	}

}
