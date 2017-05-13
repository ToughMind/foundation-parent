package lq.data_structure.Sort;

/**
 * @Description 待排序的顺序表类描述 
 * @author liuquan
 * @time 2016年1月3日 上午11:21:33
 */
public class SeqList {
	private RecordNode[] r; //顺序表的结点数组
	private int curlen; //顺序表长度，即记录个数
	
	public SeqList(int maxSize) {
		this.r = new RecordNode[maxSize];
		this.curlen = 0;
	}

	public RecordNode[] getR() {
		return r;
	}

	public int getCurlen() {
		return curlen;
	}
	
	/**
	 * @description 往顺序表弟i个结点之前插入一个 RecordNode类型的结点x
	 * @param i
	 * @param x
	 * @throws Exception
	 * @time 2016年1月3日 上午11:27:09
	 */
	public void insert(int i, RecordNode x) throws Exception{
		if(curlen == r.length)
			throw new Exception("顺序表已满");
		if(i < 0 || i > curlen)
			throw new Exception("插入位置不合理");
		// 插入位置及之后的数据元素后移
		for(int j = curlen; j > i; j--)
			r[j] = r[j-1];
		r[i] = x;
		this.curlen++;		
	} 	 
}
