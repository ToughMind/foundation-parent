package lq.lq.design_pattern.factorymethod;

/**
 * @description 中文翻译的工厂实现类 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class CNFactoryMethod implements IFactoryMethod{

	@Override
	public Translate getTranslate() {
		
		return new CNTranslate();
		
	}
 
}
