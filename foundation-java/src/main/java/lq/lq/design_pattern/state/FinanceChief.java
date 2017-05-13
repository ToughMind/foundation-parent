package lq.lq.design_pattern.state;

public class FinanceChief extends Leader {

	@Override
	public void approve(Bill bill) {
		bill.setLeader(new GeneralManager());
		System.out.println("财务经理审批，修改报支金额为16000");
		bill.setMoney(16000);
	}

	@Override
	public void approve(Document document) {
		document.setLeader(new GeneralManager());
		System.out.println("财务经理审批公文，然后送给总经理");
	}
}
