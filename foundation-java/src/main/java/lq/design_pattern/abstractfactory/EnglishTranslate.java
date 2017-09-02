package lq.design_pattern.abstractfactory;

public class EnglishTranslate implements Translate {

	@Override
	public String sayTxt(String txt) {
		
		return "Hello:" + txt;
		
	}

}
