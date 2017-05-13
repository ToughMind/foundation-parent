package lq.lq.design_pattern.chain;

public class SectionChief extends Leader {

	@Override
	public void approve(Bill bill) {
		if (bill.getMoney() < 1000) {
			System.out.println("处长审批");
		} else if (super.getLeader() != null) {
			super.getLeader().approve(bill);
		}

	}
}