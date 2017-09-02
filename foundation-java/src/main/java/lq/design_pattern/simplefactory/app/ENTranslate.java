package lq.design_pattern.simplefactory.app;

/**
 * @description 定义英文翻译的实现类  相当与简单工厂模式的Product2 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class ENTranslate implements Translate{

	@Override
	public String sayTxt(String txt) { 
		return "hello" + txt;
	} 
	
}
