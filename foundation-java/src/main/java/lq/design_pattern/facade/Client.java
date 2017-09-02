package lq.design_pattern.facade;
public class Client {
	public static void main (String[] args) {
		Computer computer = (Computer)PropertiesUtil.getInstance();

		System.out.println("100265本月的工资为：" + computer.doSalary("100265"));
    }
}
