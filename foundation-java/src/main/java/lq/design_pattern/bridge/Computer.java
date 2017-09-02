package lq.design_pattern.bridge;

public abstract class Computer {
	public Computer(Produce produce) {
		this.produce = produce;
	}

	private Produce produce;

	public void sale() {
		produce.sale();
	}
}