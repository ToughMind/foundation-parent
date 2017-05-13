package lq.lq.design_pattern.composite;
public class Client {
	public static void main (String[] args) { 
		//进行加减乘除的符合运算
        Composite salaryComputer = new Composite();
        salaryComputer.add(new Add());
        salaryComputer.add(new Multiplication());
        salaryComputer.add(new Division());
        salaryComputer.add(new Subtract());
        salaryComputer.computer(1000, 15);
    }
}
