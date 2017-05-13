package lq.pre20170513.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 从类似如下的文本文件中读取出所有的姓名，并打印出重复的姓名和重复的次数，并按重复次数排序：
 * 1,张三,28
 * 2,李四,35
 * 3,张三,28
 * 4,王五,35
 * 5,张三,28
 * 6,李四,35
 * 7,赵六,28
 * 8,田七,35 
 */
public class Test2 {
	public static void main(String[] args) {
		Map result = new HashMap(); // 将名字作为key，出现次数作为value
		InputStream is = Test2.class.getResourceAsStream("4.txt");
		BufferedReader bd = new BufferedReader(new InputStreamReader(is));
		String str = null;
		try{
			while((str = bd.readLine()) != null){
				//将数据转成map
				dealLine(str, result);
			}
			sort(result);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void sort(Map result) { 
		TreeSet tree = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2){
				User u1 = (User) o1;
				User u2 = (User) o2;
				if(u1.value < u2.value)
					return -1;
				else if(u1.value > u2.value)
					return 1;
				else
					return u1.name.compareTo(u2.name);
			}
		});	
		Iterator i = result.keySet().iterator();
		while(i.hasNext()){
			String name = (String) i.next();
			Integer value = (Integer) result.get(name);
			if(value > 1)
				tree.add(new User(name, value));
		}
		print(tree);
		
	}

	private static void print(TreeSet tree) { 
		Iterator i = tree.iterator();
		while(i.hasNext()){
			User u = (User)i.next();
			System.out.println(u.name + ":" + u.value);
		}
		
	}

	private static void dealLine(String str, Map result) {
		 if(!str.trim().isEmpty()){
			 String[] s = str.split(",");
			 if(s.length == 3){
				 String name = s[1];
				 Integer value = (Integer) result.get(name);
				 if(value == null)
					 value = 0;
				 result.put(name, value+1);
			 }
		 }		
	}
	
	static class User{
		public String name;
		public Integer value;
		public User(String name, Integer value){
			this.name = name;
			this.value = value;
		}
		
		public boolean equals(Object obj){
			// 下面代码没有执行，说明往treeset中增加数据时，不会使用到equals方法
			boolean result = super.equals(obj);
			System.out.println(result);
			return result;
		}
	}
}
