package lq.lq.design_pattern.abstractfactory;

public class Client {
	
	public static void main (String[] args) {		
		Factory factory = new ChineseFactoryMethod();
		Translate translate = factory.getTranslate();
		Interpret interpret = factory.getInterpret();
		Speech speech = factory.getSpeech();
		System.out.println(translate.sayTxt("测试") + "---" + interpret.doInterpret("测试 ") + "---" + speech.doSpeech("测试 "));

		factory = new EnglishFactoryMethod();
		translate = factory.getTranslate();
		interpret = factory.getInterpret();
		speech = factory.getSpeech();
		System.out.println(translate.sayTxt("测试") + "---" + interpret.doInterpret("测试 ") + "---" + speech.doSpeech("测试 "));
    }
	
}
