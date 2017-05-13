package lq.data_structure.Stack;

/**
 * @Description 栈的抽象数据类型结构 
 * @author liuquan
 * @time 2015年12月23日 上午12:16:02
 */
public interface IStack { 
	
	public void clear();
	
	public boolean isEmpty();
	
	public int length();
	
	public Object peek();
	
	public void push(Object x) throws Exception;
	
	public Object pop();
	
	public void display();
	
}
