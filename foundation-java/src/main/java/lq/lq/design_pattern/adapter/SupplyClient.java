package lq.lq.design_pattern.adapter;

import java.util.HashMap;
import java.util.Map;
public class SupplyClient {
	
	public static void main (String[] args) {	
		People people = new People();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map = people.getOrgName();
		PeopleAdapter list = new PeopleAdapter(map);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i + 1));
		}
    }
}