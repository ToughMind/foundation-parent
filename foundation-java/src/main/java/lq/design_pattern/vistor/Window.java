package lq.design_pattern.vistor;

public abstract class Window {
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public abstract void service();
}
