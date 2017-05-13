package lq.data_structure.Stack;

/**
 * @Description 顺序栈 
 * @author liuquan
 * @time 2015年12月23日 上午12:05:45
 */
public class SqStack implements IStack {
	private Object[] stackElem;
	//在非空栈中，top始终指向栈顶元素的下一个存储位置；当栈为空时，top值为0
	private int top;

	public SqStack(int maxSize) {
		top = 0;
		stackElem = new Object[maxSize];
	}

	public void clear() {
		top = 0;
	}

	public boolean isEmpty() {
		return top == 0;
	}

	public int length() {
		return top;
	}
	
	/**
	 * @description 取栈顶元素
	 * @return
	 * @time 2015年12月23日 上午12:10:15
	 */
	public Object peek() {
		if (!isEmpty()) {
			return stackElem[top - 1];
		} else
			return null;
	}

	/**
	 * @description 入栈 
	 * @param x
	 * @throws Exception
	 * @time 2015年12月23日 上午12:11:59
	 */
	public void push(Object x) throws Exception {
		if (top == stackElem.length)
			throw new Exception("栈已满");
		else
			stackElem[top++] = x;
	}

	/**
	 * @description 出栈 
	 * @return
	 * @time 2015年12月23日 上午12:12:09
	 */
	public Object pop() {
		if (isEmpty())
			return null;
		else
			return stackElem[--top];
	}
 
	public void display() {
		for (int i = top - 1; i >= 0; i--) {
			System.out.println(stackElem[i].toString());
		}
	}

}
