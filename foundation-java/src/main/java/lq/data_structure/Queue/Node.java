package lq.data_structure.Queue;

/**
 * @description 结点结构  在很多数据结构中可以通用的
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
public class Node {
	private Object data;
	private Node next;

	public Node() {
		this(null, null);
	}

	public Node(Object data) {
		this(data, null);
	}

	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
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
