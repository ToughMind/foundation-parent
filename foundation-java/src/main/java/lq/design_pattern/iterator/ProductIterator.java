package lq.design_pattern.iterator;

public class ProductIterator implements Iterator {
	private ProductList list;
	private int count;

	public ProductIterator(ProductList list) {
		this.list = list;
		this.count = list.count();
	}

	@Override
	public boolean hasNext() {
		boolean flag = true;
		if (count == 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	public Product next() {
		Product product;
		product = (Product) list.getList().get(--count);
		return product;
	}

}
