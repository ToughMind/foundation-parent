package lq.data_structure.Sort;

/**
 * @Description 排序算法针对顺序表进行操作 其记录类如下
 * @author liuquan
 * @time 2016年1月3日 上午1:30:58
 */
public class RecordNode {
	
	private Comparable key; // 关键字
	private Object element; // 数据元素

	public RecordNode(Comparable key) {
		super();
		this.key = key;
	}

	public RecordNode(Comparable key, Object element) {
		super();
		this.key = key;
		this.element = element;
	}

	public Comparable getKey() {
		return key;
	}

	public void setKey(Comparable key) {
		this.key = key;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
}

/**
 * @Description 关键字类 实现了 Comparable接口
 * @author liuquan
 * @time 2016年1月3日 上午1:42:37
 */
class keyType implements Comparable<keyType> {

	private int key;

	public keyType() {

	}

	public keyType(int key) {
		super();
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int compareTo(keyType another) {
		int thisKey = this.key;
		int anotherKey = another.key;
		return (thisKey < anotherKey ? -1 : (thisKey == anotherKey ? 0 : 1));
	}
	
}

/**
 * @Description 实际应用中，element可以定义为不同的具体类，以下就是一个示例
 * @author liuquan
 * @time 2016年1月3日 上午1:45:04
 */
class ElementType {
	
	private String data; // 用户可以自定义其他数据项

	public ElementType() {

	}

	public ElementType(String data) {
		super();
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
