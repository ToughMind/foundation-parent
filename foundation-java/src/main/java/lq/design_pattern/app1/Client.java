package lq.design_pattern.app1;

public class Client {
	public static void main(String[] argv) {
		People people = new People();
		people.setName("张三");
		people.setAge("18岁");
		people.setSex("男");
		System.out.println("第一次创建的对象：" + "姓名：" + people.getName() + ",年龄："
				+ people.getAge() + ",性别：" + people.getSex());
		Memento memento = people.getMemento();
		people.setName("王五");
		people.setAge("28岁");
		people.setSex("女");
		System.out.println("第二次创建的对象：" + "姓名：" + people.getName() + ",年龄："
				+ people.getAge() + ",性别：" + people.getSex());
		people.setMemento(memento);
		System.out.println("恢复成第一次创建的对象：" + "姓名：" + people.getName() + ",年龄："
				+ people.getAge() + ",性别：" + people.getSex());
	}
}
