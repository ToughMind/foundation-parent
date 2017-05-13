package lq.lq.design_pattern.abstractfactory;

public class EnglishInterpret implements Interpret {

	@Override
	public String doInterpret(String txt) {
		
		return "English Interpret:" + txt;
		
	}

}
