package lq.design_pattern.adapter;
import java.util.ArrayList;
import java.util.Map;


public class PeopleAdapter extends ArrayList<Object>{
	private Map<?, ?> map;
	public PeopleAdapter(Map<?, ?> map) {
		super();
		this.map = map;
	}

	public int size() {
        return map.size();
    }

	public Object get(int i) {
		return map.get(i);
	}
}
