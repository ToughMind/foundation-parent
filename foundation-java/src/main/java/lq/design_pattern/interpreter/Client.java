package lq.design_pattern.interpreter;

public class Client {
	public static void main (String[] args) {
		Interpreter add = new Add();
		add.parse(new Context("1 + 2 + 3"));
		Interpreter subtract = new Subtract();
		subtract.parse(new Context("1 - 2 - 8"));
		Interpreter multiplication = new Multiplication();
		multiplication.parse(new Context("1 * 2 * 8"));
		Interpreter division = new Division();
		division.parse(new Context("1 / 2 / 2"));
    }
}
