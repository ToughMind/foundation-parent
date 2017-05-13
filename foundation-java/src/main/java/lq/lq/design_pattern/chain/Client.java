package lq.lq.design_pattern.chain;

public class Client {
	public static void main(String[] args) {
		Bill bill = new Bill();
		bill.setMoney(15000);
		Leader leader1 = new SectionChief();
		Leader leader2 = new DepartmentChief();
		Leader leader3 = new FinanceChief();
		Leader leader4 = new GeneralManager();
		leader1.setLeader(leader2);
		leader2.setLeader(leader3);
		leader3.setLeader(leader4);
		leader1.approve(bill);
	}
}
