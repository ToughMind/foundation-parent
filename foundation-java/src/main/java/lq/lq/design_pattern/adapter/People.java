package lq.lq.design_pattern.adapter;
import java.util.HashMap;
import java.util.Map;

public class People {
	public Map<Object, Object> getOrgName() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put(1, "人事处");
		map.put(2, "行政处");
		map.put(3, "财务处");
		map.put(4, "供应处");
		return map;
	}
}