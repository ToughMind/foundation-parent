package lq.java.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试需要的配置文件包含到该基础测试类中{@code ContextConfiguration}
 * 
 * 启用不同环境的配置{@code ActiveProfiles}
 * 
 * 实际的测试类只需继承该基础类并在测试方法上加@Test注解，即可进行单元测试
 * 
 * @author 刘泉
 * @date 2016年9月30日 下午6:46:44
 */
// 指定bean注入的配置文件
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
// 使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class) 
@ActiveProfiles(profiles = {"dev"})
public abstract class BaseTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void baseTest() {
		logger.error("如果看到这句话，说明单元测试环境搭建成功");
	}
}
