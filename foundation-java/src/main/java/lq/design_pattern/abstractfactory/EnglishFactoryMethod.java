package lq.design_pattern.abstractfactory;

/**
 * @description 英文工厂具体实现类  处理一系列英文产品（Translate、Interpret、Speech） 
 * 
 * @author liuquan
 * @date  2015年12月21日
 */
public class EnglishFactoryMethod extends Factory{

	@Override
	protected Translate getTranslate() {
		return new EnglishTranslate();
	}
	@Override
	protected Interpret getInterpret() {
		return new EnglishInterpret();
	}
	@Override
	protected Speech getSpeech() {
		return new EnglishSpeech();
	}
	
}
