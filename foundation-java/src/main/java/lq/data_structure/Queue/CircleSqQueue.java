package lq.data_structure.Queue;
 
/**
 * @description 循环顺序队列，少用一个存储单元作为队空和队满的判断依据
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
public class CircleSqQueue implements IQueue {
	private Object[] queueElem; //队列存储空间
	private int front; //队头，队列非空则指向队头元素
	private int rear; //队尾，队列非空则指向队尾元素

	public CircleSqQueue(int maxSize) {
		front = rear = 0;
		queueElem = new Object[maxSize];
	}

	public void clear() {
		front = rear = 0;
	}

	public boolean isEmpty() {
		return rear == front;
	}

	
	public int length() {
		// rear可能小于front
		return (rear - front + queueElem.length) % queueElem.length;
	}

	/**
	 * @description 读取队首元素 
	 * @return
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public Object peek() { 
		// 判断队列是否为空
		if (front == rear)
			return null;
		else
			return queueElem[front];
	}

	/**
	 * @description 入队 
	 * @param x
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public void offer(Object x) throws Exception {
		// 判断队列是否已满
		if ((rear + 1) % queueElem.length == front)
			throw new Exception("队列已满");
		else {
			queueElem[rear] = x;
			rear = (rear + 1) % queueElem.length;
		}
	}

	/**
	 * @description 出队 
	 * @return
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public Object poll() {
		// 判断队列是否为空
		if (front == rear)
			return null;
		else {
			Object t = queueElem[front];
			front = (front + 1) % queueElem.length;
			return t;
		}
	}

	public void display() {
		if (!isEmpty()) {
			for (int i = front; i != rear; i = (i + 1) % queueElem.length)
				System.out.print(queueElem[i].toString() + " ");
		} 
		else {
			System.out.println("此队列为空");
		}
	}

}
