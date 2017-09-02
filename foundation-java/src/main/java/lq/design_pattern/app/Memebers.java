package lq.design_pattern.app;

import java.util.ArrayList;
import java.util.List;

public class Memebers {
	private List<Object> list = new ArrayList<Object>();

	// 添加会员
	public void add(Object object) {
		list.add(object);
	}

	// 移除会员
	public void remove(Object object) {
		list.remove(object);
	}

	// 获取所有会员
	public List getList() {
		return list;
	}

}
