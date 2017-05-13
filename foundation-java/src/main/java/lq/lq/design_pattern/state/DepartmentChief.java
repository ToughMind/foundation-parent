package lq.lq.design_pattern.state;

public class DepartmentChief extends Leader {

	@Override
	public void approve(Bill bill) {
		bill.setLeader(new FinanceChief());
		System.out.println("部长审批，修改报支金额为6000");
		bill.setMoney(6000);
	}

	@Override
	public void approve(Document document) {
		document.setLeader(new FinanceChief());
		System.out.println("部长审批公文，然后送给财务经理");
	}

}
