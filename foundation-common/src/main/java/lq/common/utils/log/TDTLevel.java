package lq.common.utils.log;

import org.apache.log4j.Level;

/**
 * 自定义log4j级别。
 * 
 * @author 刘泉
 * @data 2016年10月17日 下午8:29:52
 */
public class TDTLevel extends Level {

	private static final long serialVersionUID = 1L;

	public static final int REMIND_INT = Level.INFO_INT + 1;
	private static final String REMIND_STR = "REMIND";

	public static final TDTLevel REMIND = new TDTLevel(REMIND_INT, REMIND_STR, 6);

	protected TDTLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}

	public static Level toLevel(String sArg) {
		return (Level) toLevel(sArg, TDTLevel.REMIND);
	}

	public static Level toLevel(String sArg, Level defaultValue) {
		if (sArg == null) {
			return defaultValue;
		}
		String val = sArg.toUpperCase();
		if (val.equals(REMIND_STR)) {
			return TDTLevel.REMIND;
		}
		return Level.toLevel(sArg, defaultValue);
	}

	public static Level toLevel(int i) throws IllegalArgumentException {
		switch (i) {
		case REMIND_INT:
			return TDTLevel.REMIND;
		}
		return Level.toLevel(i);
	}

}
