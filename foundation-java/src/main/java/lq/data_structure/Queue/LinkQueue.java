package lq.data_structure.Queue;

/**
 * @description 链队列的结构描述 
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
public class LinkQueue implements IQueue {
	private Node front; // 队首指针
	private Node rear; // 队尾指针	

	public LinkQueue() {
		front = rear = null;
	}

	public void clear() {
		front = rear = null;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public int length() {
		int length = 0;		
		if (!isEmpty()) { // 队列非空
			Node p = front;
			while (p != null) {
				p = p.getNext();
				++length;
			}
		}
		return length;
	}

	/**
	 * @description 取队首元素 
	 * @return
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public Object peek() {
		if (front != null) // 队列非空
			return front.getData();
		else
			return null;
	}

	/**
	 * @description 入队 
	 * @param x
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public void offer(Object x) throws Exception {
		Node p = new Node(x);
		if (front != null) {
			rear.setNext(p);
			rear = p;
		} 
		else			
			front = rear = p; 
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
				System.out.print(p.getData() + " ");
				p = p.getNext();
			}
		} 
		else {
			System.out.println("此队列为空");
		}
	}
}
