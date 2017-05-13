package lq.lq.design_pattern.bridge;

public class Dell implements Produce {
	@Override
	public void sale() {
		System.out.print("销售戴尔");
	}
}
