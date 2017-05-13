package lq.data_structure.Stack;

/**
 * @Description 链栈的结点结构 
 * @author liuquan
 * @time 2015年12月23日 上午12:16:20
 */
class Node {
	private Object data;
	private Node next;

	public Node() {
		this(null);
	}

	public Node(Object data) {
		this.data = data;
		this.next = null;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}

/**
 * @Description 链栈结构
 * @author liuquan
 * @time 2015年12月23日 上午12:18:09
 */
public class LinkStack implements IStack {
	private Node top;

	public void clear() {
		top = null;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int length() {
		Node p = top;
		int length = 0;
		while (p != null) {
			p = p.getNext();
			++length;
		}
		return length;
	}

	public Object peek() {
		if (!isEmpty())
			return top.getData();
		else
			return null;
	}

	/**
	 * @description 入栈 
	 * @param x
	 * @time 2015年12月23日 上午12:19:39
	 */
	public void push(Object x) {
		Node p = new Node(x);
		p.setNext(top);
		top = p;
	}

	/**
	 * @description 出栈 
	 * @return
	 * @time 2015年12月23日 上午12:19:48
	 */
	public Object pop() {
		if (isEmpty())
			return null;
		else {
			Node p = top;
			top = top.getNext();
			return p.getData();
		}
	}

	public void display() {
		Node p = top;
		while (p != null) {
			System.out.println(p.getData().toString() + " ");
			p = p.getNext();
		}
	}
	
}
