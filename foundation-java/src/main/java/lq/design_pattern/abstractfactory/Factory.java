package lq.design_pattern.abstractfactory;

/**
 * @description 抽象工厂类  包含了三个产品（Translate、Interpret、Speech） 
 * 
 * @author liuquan
 * @date  2015年12月21日
 */
public abstract class Factory {
	protected abstract Translate getTranslate();
	protected abstract Interpret getInterpret();
	protected abstract Speech getSpeech();
	 
    public String sayTxt(String txt){
    	Translate translate = getTranslate();
    	Interpret interpret = getInterpret();
    	Speech speech = getSpeech();
        return translate.sayTxt(txt) + "---" + interpret.doInterpret(txt) + "---" + speech.doSpeech(txt);
    }
		
}