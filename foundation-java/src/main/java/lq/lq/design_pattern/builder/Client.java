package lq.lq.design_pattern.builder;

public class Client {
	public static void main (String[] args) {
		Director director = new Director();
		
		director.setFactory(new WorkerFactory());
		System.out.println(director.doSalary("劳务工")); 
    }
}