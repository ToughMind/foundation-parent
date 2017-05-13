package lq.common.utils.log;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送处理类。
 * 
 * @author 刘泉
 * @data 2016年10月17日 下午8:48:21
 */
public class MockTriggeringEventEvaluator {

	public final static class ATriggeringEventEvaluator implements TriggeringEventEvaluator {

		private static boolean isSend = false;

		/**
		 * 保证只能发送一次
		 */
		@Override
		public boolean isTriggeringEvent(LoggingEvent paramLoggingEvent) {
			if (!isSend) {
				isSend = true;
				LoggerFactory.getLogger(getClass()).info("已发业务A类型邮件提醒！");
				return true;
			}
			return false;
		}
	}

	public static final class BTriggeringEventEvaluator implements TriggeringEventEvaluator {
		@Override
		public boolean isTriggeringEvent(LoggingEvent paramLoggingEvent) {
			LoggerFactory.getLogger(getClass()).info("已发业务B类型邮件提醒！");
			return true;
		}
	}

}
