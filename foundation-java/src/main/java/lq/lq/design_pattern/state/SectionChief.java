package lq.lq.design_pattern.state;

public class SectionChief extends Leader {

	@Override
	public void approve(Bill bill) {
		bill.setLeader(new DepartmentChief());
		System.out.println("处长审批，修改报支金额为2000");
		bill.setMoney(2000);
	}

	@Override
	public void approve(Document document) {
		document.setLeader(new DepartmentChief());
		System.out.println("处长审批公文，然后送给部长");
	}
}
