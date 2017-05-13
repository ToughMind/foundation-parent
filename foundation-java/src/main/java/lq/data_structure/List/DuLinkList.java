package lq.data_structure.List;

import java.util.Scanner;

/**
 * @Description 双向循环链表的结点结构 
 * @author liuquan
 * @time 2015年12月22日 下午10:56:18
 */
class DuLNode {
	private Object data;
	private DuLNode prior;
	private DuLNode next;

	public DuLNode() {
		this(null);
	}

	public DuLNode(Object data) {
		this.data = data;
		this.prior = null;
		this.next = null;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public DuLNode getPrior() {
		return prior;
	}

	public void setPrior(DuLNode prior) {
		this.prior = prior;
	}

	public DuLNode getNext() {
		return next;
	}

	public void setNext(DuLNode next) {
		this.next = next;
	}
}

/**
 * @Description 带头结点的双向循环链表
 * @author liuquan
 * @time 2015年12月22日 下午11:00:03
 */
public class DuLinkList implements IList {
	private DuLNode head;

	public DuLinkList() {
		head = new DuLNode();
		head.setPrior(head);
		head.setNext(head);
	}

	// 头插法逆向创建双向循环链表
	public DuLinkList(int n) throws Exception {
		this();
		Scanner sc = new Scanner(System.in);
		for (int j = 0; j < n; j++) {
			insert(0, sc.next());
		}
	}

	public DuLNode getHead() {
		return head;
	}

	public void setHead(DuLNode head) {
		this.head = head;
	}

	public void clear() {
		head.setPrior(head);
		head.setNext(head);
	}

	public boolean isEmpty() {
		return head.getNext().equals(head);
	}

	public int length() {
		DuLNode p = head.getNext();
		int length = 0;
		while (!p.equals(head)) {
			p = p.getNext();
			++length;
		}
		return length;
	}

	public Object get(int i) throws Exception {
		DuLNode p = head.getNext();
		int j = 0;
		while (!p.equals(head) && j < i) {
			p = p.getNext();
			++j;
		}
		if (j > i || p.equals(head)) {
			throw new Exception("第" + i + "个元素不存在");
		}
		return p.getData();
	}

	/**
	 * @description 注意这里：双向链表的增加找的是当前结点，而单向链表的增加找的是前驱
	 * @param i
	 * @param x
	 * @throws Exception
	 * @time 2015年12月22日 下午10:58:04
	 */
	public void insert(int i, Object x) throws Exception {
		DuLNode p = head.getNext();
		int j = 0;
		while (!p.equals(head) && j < i) {
			p = p.getNext();
			++j;
		}
		if (j != i)
			throw new Exception("插入位置不合法");
		DuLNode s = new DuLNode(x);
		p.getPrior().setNext(s);
		s.setPrior(p.getPrior());
		s.setNext(p);
		p.setPrior(s);

	}

	
	public void remove(int i) throws Exception {
		DuLNode p = head.getNext();
		int j = 0;
		while (!p.equals(head) && j < i) {
			p = p.getNext();
			++j;
		}
		if (j != i || p.equals(head))
			throw new Exception("删除位置不合法");
		p.getPrior().setNext(p.getNext());
		p.getNext().setPrior(p.getPrior());
	}

	public int indexOf(Object x) {
		DuLNode p = head.getNext();
		int j = 0;
		while (!p.equals(head) && !p.getData().equals(x)) {
			p = p.getNext();
			++j;
		}
		if (!p.equals(head))
			return j;
		else
			return -1;
	}

	public void display() {
		DuLNode node = head.getNext();
		while (!node.equals(head)) {
			System.out.print(node.getData() + " ");
			node = node.getNext();
		}
		System.out.println();
	}
	
}
