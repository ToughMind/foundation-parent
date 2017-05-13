package lq.data_structure.List;

/**
 * @Description 线性表的抽象数据类型描述 
 * @author liuquan
 * @time 2015年12月22日 下午10:51:59
 */
public interface IList {
	 
	public void clear();

	public boolean isEmpty();

	public int length();

	public Object get(int i) throws Exception;

	/**
	 * @description 在线性表的第i个元素之前插入一个值为x的数据元素 
	 * @param i  0<=i<=length()
	 * @param x
	 * @throws Exception
	 * @time 2015年12月22日 下午9:21:24
	 */
	public void insert(int i, Object x) throws Exception;
 
	public void remove(int i) throws Exception;

	public int indexOf(Object x);

	public void display();
}
