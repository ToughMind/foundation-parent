package lq.lq.design_pattern.chain;

public class GeneralManager extends Leader {

	@Override
	public void approve(Bill bill) {
		if (bill.getMoney() >= 10000) {
			System.out.println("总经理审批");
		} else if (super.getLeader() != null) {
			super.getLeader().approve(bill);
		}
	}

}
