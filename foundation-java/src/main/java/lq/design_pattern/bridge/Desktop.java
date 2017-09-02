package lq.design_pattern.bridge;

public class Desktop extends Computer {
	public Desktop(Produce produce) {
		super(produce);
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("台式机");
	}
}