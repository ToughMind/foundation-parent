package lq.pre20170513.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 *  50个人，每遇到3的倍数出局，最后一个是谁
 *
 */
public class Test1 {
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<Integer>(); 
		for(int i = 1; i <= 10; i++){
			list.add(i);
		}
		int index = -1;
		while(list.size() > 1){
			index = (index + 3) % list.size();
			System.out.print(list.get(index) + ",");
			list.remove(index--);
		}
		System.out.println(list.get(0)); 
	} 
}
