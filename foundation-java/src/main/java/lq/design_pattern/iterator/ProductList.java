package lq.design_pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
	private List<Product> list = new ArrayList<Product>();
	private int count;

	public void add(Product product) {
		list.add(product);
		count++;
	}

	public void remove(Product product) {
		list.remove(product);
		count--;
	}

	public int count() {
		return count;
	}

	public List<Product> getList() {
		return list;
	}

	public Iterator CreateIterator() {
		return new ProductIterator(this);
	}

}
