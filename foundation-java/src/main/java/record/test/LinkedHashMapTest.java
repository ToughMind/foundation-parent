package record.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

public class LinkedHashMapTest {
	public static void main(String[] args) {
		Map<String, Map<String, List<String>>> map1 = new LinkedHashMap();
		String[] str1 = new String[] { "A", "G", "F", "C", "H" };
		String[] str2 = new String[] { "a", "g", "f", "c", "h" };
		for (int i = 0; i < 5; i++) {
			Map<String, List<String>> map2 = new LinkedHashMap();
			map1.put(str1[i], map2);
			List<String> list = new ArrayList<>();
			for (int j = 1; j < 6; j++) {
				list.add(str2[i] + j);
			}
			map2.put(str2[i], list);
		}
		List<String> result = new ArrayList<>();
		Iterator<Entry<String, Map<String, List<String>>>> it = map1.entrySet().iterator();
		while (it.hasNext()) {
			Iterator<Entry<String, List<String>>> it2 = it.next().getValue().entrySet().iterator();
			while (it2.hasNext()) {
				result.addAll(it2.next().getValue());
			}
		}
		System.out.println(JSON.toJSONString(map1));
		System.out.println(JSON.toJSONString(result));
	}
}
