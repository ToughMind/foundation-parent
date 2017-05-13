package lq.java.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lq.java.controller.UserController;

/**
 * 跳转控制器：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午9:40:43
 */
public class UserMain {
	
	@Autowired
	private UserController userController;
	
	public UserController getController() {
		return userController;
	}
	
	private static ApplicationContext ac;
	
	static {
		// 注意 一定要指定profile
		System.setProperty("spring.profiles.active", "dev");
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static void main(String[] args) {
		UserMain main = (UserMain)ac.getBean("UserMain");
		main.getController().getById();
	}
	
}
