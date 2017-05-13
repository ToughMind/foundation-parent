package lq.lq.design_pattern.abstractfactory;

public class ChineseInterpret implements Interpret {

	@Override
	public String doInterpret(String txt) {
		return "解释中文语义" + txt;
	}

}