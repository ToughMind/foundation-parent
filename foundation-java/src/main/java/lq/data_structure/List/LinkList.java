package lq.data_structure.List;

import java.util.Scanner;

/**
 * @Description 单向链表的结点结构 
 * @author liuquan
 * @time 2015年12月22日 下午10:51:31
 */
class Node {
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

/**
 * @Description 线性表的链式存储  带头结点的单向链表
 * @author liuquan
 * @time 2015年12月22日 下午10:51:09
 */
public class LinkList implements IList {
	private Node head;

	public LinkList() {
		head = new Node();
	}

	public LinkList(int n, boolean Order) throws Exception {
		this();
		if (Order)
			//用尾插法顺序建立单链表
			create1(n);
		else
			//用头插法顺序创建单链表
			create2(n);
	}
	

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	// 尾插法顺序建立单链表
	private void create2(int n) throws Exception {
		Scanner sc = new Scanner(System.in);
		for (int j = 0; j < n; j++) {
			insert(length(), sc.next());
		} 
	}

	// 头插法逆序建立单链表
	private void create1(int n) throws Exception {
		Scanner sc = new Scanner(System.in);
		for (int j = 0; j < n; j++) {
			insert(0, sc.next());
		}
	}

	public void clear() { 
		head.setNext(null);
	}

	public boolean isEmpty() {
		return head.getNext() == null;
	}

	public int length() {
		Node p = head.getNext();
		int length = 0;
		while (p != null) {
			p = p.getNext();
			++length;
		}
		return length;
	}

	public Object get(int i) throws Exception {
		// p指向首结点
		Node p = head.getNext();
		// j为计数器
		int j = 0;
		while (p != null && j < i) {
			p = p.getNext();
			++j;
		}
		// p=null就代表i已经超过length()-1了
		if (i < 0 || p == null) {
			throw new Exception("第" + i + "个元素不存在");
		}
		return p.getData();
	}

	/**
	 * @description 带头结点的单链表上的插入 
	 * @param i
	 * @param x
	 * @throws Exception
	 * @time 2015年12月22日 下午10:15:16
	 */
	public void insert(int i, Object x) throws Exception {
		Node p = head;
		int j = -1;
		// 寻找第i个位置的前驱(即第i-1个位置)
		while (p != null && j < i - 1) {
			p = p.getNext();
			++j;
		}
		//j!=i-1是判断i是负数，等同于i<0;p==null是判断i的位置超过了最大长度(length())
		if (j != i - 1 || p == null)
			throw new Exception("插入位置不合法");
		
		Node s = new Node(x);
		s.setNext(p.getNext());
		p.setNext(s);
	}

	public void remove(int i) throws Exception {
		Node p = head;
		int j = -1;
		// 寻找第i个位置的前驱  p.getNext() != null为判断i必须<length(),也就是最多到最后一个元素的位置（length()-1）
		while (p.getNext() != null && j < i - 1) {
			p = p.getNext();
			++j;
		}
		if (j != i - 1 || p.getNext() == null)
			throw new Exception("删除位置不合法");
		
		p.setNext(p.getNext().getNext());

	}

	/**
	 * @description 按值查找首次出现的位置，没有找到则返回-1 
	 * @param x
	 * @return
	 * @time 2015年12月22日 下午10:13:46
	 */
	public int indexOf(Object x) {
		// p指向首结点，j是计数器
		Node p = head.getNext();
		int j = 0;
		while (p != null && !p.getData().equals(x)) {
			p = p.getNext();
			++j;
		}
		//若找到了该值，则p指向某一结点，不会为null
		if (p != null)
			return j;
		else
			return -1;
	}

	public void display() {
		Node node = head.getNext();
		while (node != null) {
			System.out.print(node.getData() + " ");
			node = node.getNext();
		}
		System.out.println();
	}

	/**
	 * @description 删除单链表中重复的结点 从单链表首结点开始依次将单链表中每个结点与它后面的所有结点进行比较
	 * @throws Exception
	 * @time 2015年12月22日 下午10:31:51
	 */
	public void removeRepeatElem() throws Exception {
		// p指向首结点 (区别于头结点)
		Node p = head.getNext(), q;
		while (p != null) {
			int order = indexOf(p.getData());
			q = p.getNext();
			while (q != null) {
				if (p.getData().equals(q.getData()))
					// 调用前面写好了的的删除方法
					remove(order + 1);
				else
					++order;
				q = q.getNext();
			}
			p = p.getNext();
		}
	}
                      	
	/**
	 * @description 将两个有序单链表la和lb合并成一个新的有序单链表，借助3个指针pa，pb，pc，pa指向la首结点，pb指向lb首结点，pc指向la头结点
	 * 				当pa.getData()<pb.getData(),则将pa链接到pc之后；反之则将pb链接到pc后。
	 * @param la
	 * @param lb
	 * @return
	 * @time 2015年12月22日 下午10:38:09
	 */
	public LinkList mergeList(LinkList la, LinkList lb) {
		Node pa = la.getHead().getNext();
		Node pb = lb.getHead().getNext();
		Node pc = la.getHead();
		int da, db;
		while (pa != null && pb != null) { 
			da = Integer.parseInt(pa.getData().toString());
			db = Integer.parseInt(pb.getData().toString());			 
			if (da <= db) {
				pc.setNext(pa);
				pc = pa;
				pa = pa.getNext();
			} 
			//
			else {
				pc.setNext(pb);
				pc = pb;
				pb = pb.getNext();
			}
		}
		pc.setNext(pa != null ? pa : pb);
		return la;
	}
	
}
