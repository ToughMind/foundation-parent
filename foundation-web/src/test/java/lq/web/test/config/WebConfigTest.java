package lq.web.test.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import lq.web.config.UserConfig;
import lq.web.config.UserJsonConfig;
import lq.web.config.WebConfig;
import lq.web.domain.vo.UserVO;
import lq.web.test.BaseTest;

public class WebConfigTest extends BaseTest {
	
	@Autowired
	private WebConfig webConfig;
	
	@Autowired
	private UserJsonConfig userJsonConfig;
	
	@Autowired
	private UserConfig userConfig;
	
	@Test
	public void show1() {
		System.out.println(webConfig.getEnvironment());
		System.out.println(userConfig.getUserName());
	}
	
	@Test
	public void show2() {
		System.out.println(userJsonConfig.getSrcJson());
		UserVO vo = JSON.parseObject(userJsonConfig.getSrcJson(), UserVO.class);
		System.out.println(vo.getName());
		
	}

	@Test
	public void show3() {
		System.out.println(userConfig.getUserName());
	}
}
