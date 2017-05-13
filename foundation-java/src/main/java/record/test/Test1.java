package record.test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

public class Test1 {
	public static void main(String[] args) {
		// f1();
		// f2();
		// f3();
		// f4();
		// f5();
		//System.out.println("fdsfsd=" + "4343" + ",jdgkljd=" + "543534");
		System.out.println(String.format("fdsfsd=%s,jdgkljd=%s", 4343, 543534));
	}

	private static void f5() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
		list.remove(3);
		list.remove(4);
		System.out.println(JSON.toJSONString(list));
	}

	/**
	 * 测试set在加入数据集合是否可以去重。
	 * 
	 * @author 刘泉
	 * @date 2016年12月28日 上午10:23:48
	 */
	private static void f4() {
		Set<String> set = new HashSet<>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
			list.add(String.valueOf(i + 1));
		}
		for (String s : list) {
			System.out.print(s + " ");
		}
		set.addAll(list);
		for (String s : set) {
			System.err.print(s + " ");
		}
	}

	/**
	 * 测试list和set的contains方法效率。
	 * 
	 * @author 刘泉
	 * @date 2016年12月26日 下午4:14:32
	 */
	private static void f3() {
		List<String> list = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			list.add(String.valueOf(i));
		}
		start = System.currentTimeMillis();
		List<String> l = new ArrayList<>();
		for (int i = 0; i < 1000; i += 4) {
			l.add(String.valueOf(i));
		}
		for (String s : l) {
			if (list.contains(s)) {
				// System.out.println(true);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Set<String> set = new HashSet<>(list);
		for (int i = 0; i < 1000; i += 4) {
			l.add(String.valueOf(i));
		}
		for (String s : l) {
			if (set.contains(s)) {
				// System.out.println(true);
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	/**
	 * 测试map的get方法取
	 * 
	 * @author 刘泉
	 * @date 2016年12月26日 上午11:23:09
	 */
	private static void f2() {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "2");
		List<String> list = (List<String>) map.get("2");
		System.out.println(list);
	}

	/**
	 * 测试字符串替换性能比较。
	 * 
	 * @author 刘泉
	 * @date 2016年12月20日 上午10:01:23
	 */
	public static void f1() {
		String str = "请联系{0}，谢谢！";
		String result = "";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			result = str.replace("{0}", "客服");
		}
		System.out.println("[op: replace] result=" + result + ", time=" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			result = MessageFormat.format(str, "客服");
		}
		System.out.println("[op: format] result=" + result + ", time=" + (System.currentTimeMillis() - start));
	}
}
