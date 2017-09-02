package lq.design_pattern.app1;

public class People {
	private String name;
	private String sex;
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	// 备忘者
	public Memento getMemento() {
		return new Memento(this);
	}

	// 获取备忘的内容
	public void setMemento(Memento memento) {
		this.name = memento.getName();
		this.sex = memento.getSex();
		this.age = memento.getAge();
	}

}
