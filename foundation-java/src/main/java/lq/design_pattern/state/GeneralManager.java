package lq.design_pattern.state;

public class GeneralManager extends Leader {

	@Override
	public void approve(Bill bill) {
		if (bill.getMoney() >= 10000) {
			System.out.println("总经理审批");
		} else if (super.getLeader() != null) {
			super.getLeader().approve(bill);
		}
	}

	@Override
	public void approve(Document document) {
		System.out.println("总经理审批公文，流程结束");
	}
}
