package lq.lq.design_pattern.bridge;

public class Notebook extends Computer {
	public Notebook(Produce produce) {
		super(produce);
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("笔记本电脑");
	}
}
