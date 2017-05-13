package lq.lq.design_pattern.chain;

public class DepartmentChief extends Leader {

	@Override
	public void approve(Bill bill) {
		if (bill.getMoney() >= 1000 && bill.getMoney() < 5000) {
			System.out.println("部长审批");
		} else if (super.getLeader() != null) {
			super.getLeader().approve(bill);
		}
	}

}
