package lq.pre20170513.base;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet set = new TreeSet();
		set.add(new Parent(46)); 
		set.add(new Parent(1));
		set.add(new Parent(3));
		set.add(new Parent(4)); 
		//set.add(new Child());
 		set.add(new Parent(3));
 		
		Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(((Parent)it.next()).age);
        }
	}
}

class Parent implements Comparable {
	public int age = 0;

	public Parent(int age) {
		this.age = age;
	}

	public int compareTo(Object o) { 
		System.out.println("method of parent" + age); 
 		Parent o1 = (Parent) o;
 		return age > o1.age ? 1 : age < o1.age ? -1 : 0;
	}
}

class Child extends Parent {

	public Child() {
		super(3);
	}

	public int compareTo(Object o) { 
		System.out.println("method of child" + age);
		// Child o1 = (Child)o;
		return 1;
	}
}
