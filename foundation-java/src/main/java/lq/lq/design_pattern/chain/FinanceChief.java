package lq.lq.design_pattern.chain;

public class FinanceChief extends Leader {

	@Override
	public void approve(Bill bill) {
		if (bill.getMoney() >= 5000 && bill.getMoney() < 10000) {
			System.out.println("财务经理审批");
		} else if (super.getLeader() != null) {
			super.getLeader().approve(bill);
		}
	}

}
