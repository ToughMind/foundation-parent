package lq.design_pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class FinanceClient {
	
	public static void main (String[] args) { 		
		People people = new People();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map = people.getOrgName();
		for (int i = 0; i < map.size(); i++) {
			System.out.println(map.get(i + 1));
		} 
    }
}