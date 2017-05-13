package lq.data_structure.List;

/**
 * @Description 线性表的顺序存储 
 * @author liuquan
 * @time 2015年12月22日 下午10:50:11
 */
public class SqList implements IList {
	
	private Object[] listElem;	// 线性表存储空间
	private int curLen;	// 线性表的当前长度
	
	 
	/**
	 * @description 构造一个存储空间容量为maxSize的线性表
	 * @param maxSize
	 * @time 2015年12月22日 下午9:50:05
	 */	
	public SqList(int maxSize) {
		curLen = 0;
		listElem = new Object[maxSize];
	}
	
	public void clear() {
		curLen = 0;
	}

	public boolean isEmpty() {
		return curLen == 0;
	}

	public int length() {
		return curLen;
	}

	
	/**
	 * @description 读取线性表的第i个元素并返回其值
	 * @param i 0<=i<=length()
	 * @return
	 * @throws Exception
	 * @time 2015年12月22日 下午9:50:21
	 */
	public Object get(int i) throws Exception {
		if (i < 0 || i > curLen - 1)
			throw new Exception("第" + i + "个元素不存在.");
		return listElem[i];
	}
	 
	/**
	 * @description 在线性表的第i个元素之前插入一个值为x的数据元素
	 * @param i
	 * @param x
	 * @throws Exception
	 * @time 2015年12月22日 下午9:50:34
	 */
	public void insert(int i, Object x) throws Exception {
		if (curLen == listElem.length)
			throw new Exception("顺序表已满");
		if (i < 0 || i > curLen)
			throw new Exception("插入位置不合法");
		for (int j = curLen; j > i; j--) {
			listElem[j] = listElem[j - 1];
		}
		listElem[i] = x;
		curLen++;	
	}

	public void remove(int i) throws Exception {
		if (i < 0 || i > curLen - 1)
			throw new Exception("删除位置不合法");
		for (int j = i; j < curLen - 1; j++)
			listElem[j] = listElem[j + 1];
		curLen--;
	}

	/**
	 * @description 返回线性表中首次出现指定的数据元素的位序号；若线性表中不包含此数据元素，则返回-1 
	 * @param x
	 * @return
	 * @time 2015年12月22日 下午9:51:06
	 */
	public int indexOf(Object x) {
		int j = 0;
		while (j < curLen && !listElem[j].equals(x))
			j++;
		if (j < curLen)
			return j;
		else
			return -1;
	}

	public void display() {
		for (int j = 0; j < curLen; j++)
			System.out.println(listElem[j] + " ");
		System.out.println();
	}
	
}
