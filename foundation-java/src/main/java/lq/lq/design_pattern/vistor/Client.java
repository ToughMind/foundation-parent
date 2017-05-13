package lq.lq.design_pattern.vistor;

public class Client {
	public static void main(String[] args) { 
		Window window1 = new Window1();
		Window window2 = new Window2();
		Visitor visitor = new Visitor();
		visitor.visit(window1);
		visitor.visit(window2);
	}
}