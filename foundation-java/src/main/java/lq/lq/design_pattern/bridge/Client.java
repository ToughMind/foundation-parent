package lq.lq.design_pattern.bridge;

public class Client {
	public static void main(String[] args) {
		Computer computer = new Desktop(new Dell());
		computer.sale();
		computer = new Pad(new Dell());
		computer.sale();
		computer = new Notebook(new Dell());
		computer.sale();
	}
}