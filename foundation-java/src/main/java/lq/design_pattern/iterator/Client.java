package lq.design_pattern.iterator;

public class Client {
	public static void main(String[] args) { 
		Product product1 = new Product("洗衣机");
		Product product2 = new Product("电视机");
		Product product3 = new Product("电冰箱");
		Product product4 = new Product("自行车");
		Product product5 = new Product("电脑");
		ProductList productList = new ProductList();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);
		productList.add(product5);
		Iterator iterator = productList.CreateIterator();
		while (iterator.hasNext()) {
			System.out.println(((Product) iterator.next()).getName());
		}
	}
}
