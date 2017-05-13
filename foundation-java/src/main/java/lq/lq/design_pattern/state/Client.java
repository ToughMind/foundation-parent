package lq.lq.design_pattern.state;

public class Client {
	public static void main(String[] args) {
		Bill bill = new Bill();
		bill.setMoney(1000);
		bill.approve();
		bill.approve();
		bill.approve();
		bill.approve();

		Document document = new Document();
		document.approve();
		document.approve();
		document.approve();
		document.approve();
	}
}
