package lq.design_pattern.simplefactory.app;

public class Client {
	
	public static void main (String[] args) {
		SimpleFactory simpleFactory = new SimpleFactory();
		Translate translate = simpleFactory.getTranslate(1);
		System.out.println(translate.sayTxt("测试"));
		translate = simpleFactory.getTranslate(2);
		System.out.println(translate.sayTxt("测试")); 
		
		//反射形式
		translate = simpleFactory.getTranslate("simpleFactory.app.CNTranslate");
		System.out.println(translate.sayTxt("测试"));
		translate = simpleFactory.getTranslate("simpleFactory.app.ENTranslate");
		System.out.println(translate.sayTxt("测试"));
		
		//模拟spring
		translate = simpleFactory.getTranslate();
		System.out.println(translate.sayTxt("测试"));
    }
	
}
