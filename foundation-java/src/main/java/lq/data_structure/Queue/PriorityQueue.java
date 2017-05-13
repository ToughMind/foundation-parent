package lq.data_structure.Queue;

/**
 * @description 入队列的数据结构（一般数据都是设为Object，这里相当于设置一个优先级标志再结合结点值封装成的新的对象）
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
class PriorityQData {
	private Object elem; // 结点的值
	private int priority; // 结点的优先级

	public PriorityQData(Object elem, int priority) {
		this.elem = elem;
		this.priority = priority;
	}

	public Object getElem() {
		return elem;
	}

	public void setElem(Object elem) {
		this.elem = elem;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}

/**
 * @description 优先级链队列结构 
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
public class PriorityQueue implements IQueue {
	private Node front;
	private Node rear;

	public PriorityQueue() {
		front = rear = null;
	}

	public void clear() {
		front = rear = null;
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public int length() {
		int length = 0;
		if (!isEmpty()) {
			Node p = front;
			while (p != null) {
				p = p.getNext();
				++length;
			}
		}
		return length;
	}

	public Object peek() {
		if (front != null)
			return front.getData();
		else
			return null;
	}
 
	/**
	 * @description 入队  按优先级由大到小排列（ 数值越小优先级越大）
	 * @param x
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public void offer(Object x) throws Exception {
		PriorityQData pn = (PriorityQData) x;
		Node s = new Node(pn);
		if (front == null)
			front = rear = s;
		else { 
			// 在普通情况下  新插入的结点x在q与p之间
			Node p = front, q = front;
			while (p != null && pn.getPriority() >= ((PriorityQData) p.getData()).getPriority()) {
				q = p;
				p = p.getNext();
			}
			// 表示遍历到了队尾  （q指向队尾  p指向null）
			if (p == null) {
				rear.setNext(s);
				rear = s;
			}
			// 表示p的优先级大于首结点的优先级
			else if (p == front) {
				s.setNext(front);
				front = s;
			} 
			else {
				q.setNext(s);
				s.setNext(p);
			}
		}
	}

	/**
	 * @description 出队 
	 * @return
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public Object poll() {
		if (front != null) {
			Node p = front;
			front = front.getNext();
			return p.getData();
		} 
		else
			return null;
	}

	public void display() {
		if (!isEmpty()) {
			Node p = front;
			while (p != rear.getNext()) {
				PriorityQData t = (PriorityQData) p.getData();
				System.out.println(t.getElem() + "   " + t.getPriority());
				p = p.getNext();
			}
		} else {
			System.out.println("此队列为空");
		}
	}
}
