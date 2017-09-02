package lq.design_pattern.factorymethod;

/**
 * @description 英文翻译的工厂实现类 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class ENFactoryMethod implements IFactoryMethod{

	@Override
	public Translate getTranslate() {
		
		return new ENTranslate();
		
	}
}
