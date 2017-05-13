package lq.record.simple.mvc.entity;

/**
 * 管理action的类，利用manager封装类获action及相关信息后，此action管理类就负责通过action名反射找到action类，然后执行execute方法
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:55:53
 */
public class ActionManager {

	public static Action getAction(String className) {
		Action action = null;
		try {
			action = (Action) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return action;
	}

}