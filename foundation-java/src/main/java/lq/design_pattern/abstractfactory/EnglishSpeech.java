package lq.design_pattern.abstractfactory;

public class EnglishSpeech implements Speech {

	@Override
	public String doSpeech(String txt) {
		
		return "Speech Sound" + txt;
		
	}

}