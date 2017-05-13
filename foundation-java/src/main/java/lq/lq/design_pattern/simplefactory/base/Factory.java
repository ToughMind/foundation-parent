package lq.lq.design_pattern.simplefactory.base;

public class Factory{

	//根据参数创建不同对象
	public static IProduct createProduct(String productName) {
		if("1".equals(productName)){
			return new Product1();
		}
		else if("2".equals(productName)){
			return new Product2();
		}
		return null;
	}

}
