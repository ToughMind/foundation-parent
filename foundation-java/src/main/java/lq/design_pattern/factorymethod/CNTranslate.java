package lq.design_pattern.factorymethod;

/**
 * @description 定义中文翻译的实现类  相当与简单工厂模式的Product 1 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class CNTranslate implements Translate{

	@Override
	public String sayTxt(String txt) {
		return "您好" + txt;
	}

}
