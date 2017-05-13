package lq.data_structure.Queue;

/**
 * @description 队列的抽象数据类型描述 
 * 
 * @author liuquan
 * @date  2015年12月29日
 */
public interface IQueue {
	public void clear();

	public boolean isEmpty();

	public int length();

	public Object peek();

	public void offer(Object x) throws Exception;

	public Object poll();

	public void display();
}
