package lq.lq.design_pattern.bridge;

public class Pad extends Computer {
	public Pad(Produce produce) {
		super(produce);
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("平板电脑");
	}
}
