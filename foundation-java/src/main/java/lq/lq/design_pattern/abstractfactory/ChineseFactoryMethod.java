package lq.lq.design_pattern.abstractfactory;

/**
 * @description 中文工厂具体实现类  处理一系列中文产品（Translate、Interpret、Speech） 
 * 
 * @author liuquan
 * @date  2015年12月21日
 */
public class ChineseFactoryMethod extends Factory{
	
	@Override
	protected Translate getTranslate() {
		return new ChineseTranslate();
	}
	
	@Override
	protected Interpret getInterpret() {
		return new ChineseInterpret();
	}
	
	@Override
	protected Speech getSpeech() {
		return new ChineseSpeech();
	}
	
}