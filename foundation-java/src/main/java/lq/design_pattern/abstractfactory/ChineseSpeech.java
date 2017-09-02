package lq.design_pattern.abstractfactory;

public class ChineseSpeech implements Speech {

	@Override
	public String doSpeech(String txt) {
		return "语音播报" + txt;
	}

}
