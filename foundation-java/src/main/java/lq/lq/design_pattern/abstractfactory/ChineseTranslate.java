package lq.lq.design_pattern.abstractfactory;

public class ChineseTranslate implements Translate {

	@Override
	public String sayTxt(String txt) {
		return "您好" + txt;
	}
}
