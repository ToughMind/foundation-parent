package lq.design_pattern.factorymethod;

/**
 * @description 客户端测试 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class Client {
	
	public static void main (String[] args) {
		
		IFactoryMethod factoryMethod = new CNFactoryMethod();
		Translate translate = factoryMethod.getTranslate();
		System.out.println(translate.sayTxt("测试"));


		factoryMethod = new ENFactoryMethod();
		translate = factoryMethod.getTranslate();
		System.out.println(translate.sayTxt("测试"));

		
//		FactoryMethod1 factoryMethod1 = new FactoryMethod2();
//		System.out.println(factoryMethod1.sayTxt(3, "����"));
		
    }
	
}