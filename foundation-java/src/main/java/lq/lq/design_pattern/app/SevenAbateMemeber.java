package lq.lq.design_pattern.app;

public class SevenAbateMemeber implements Member {
	private double count;
	private double price;

	public double doComputer() {
		System.out.print("购物数量：" + this.getCount() + "物品单价：" + this.getPrice()
				+ "该会员打七折,");
		return this.getCount() * this.getPrice() * 0.7;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
