package lq.design_pattern.app;

public class NoAbateMemeber implements Member {
	private double count;
	private double price;

	public double doComputer() {
		System.out.print("购物数量：" + this.getCount() + "物品单价：" + this.getPrice()
				+ "该普通会员不打折,");
		return this.getCount() * this.getPrice();
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
