package lq.web.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import lq.web.config.UserConfig;
import lq.web.config.UserJsonConfig;
import lq.web.domain.vo.UserVO;
import lq.web.service.UserWebService;

/**
 * 跳转控制器：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午9:40:43
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserWebService userWebService;
	
	@Autowired
	private UserJsonConfig userJsonConfig;
	
	@Autowired
	private UserConfig UserConfig;

	@RequestMapping("/no1")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView("user");
		UserVO vo = userWebService.getById(1);
		mav.addObject("user", vo);
		UserVO vo2 = JSON.parseObject(userJsonConfig.getSrcJson(), UserVO.class);
		vo2.setName(UserConfig.getUserName());
		mav.addObject("user2", vo2);
		return mav;
	}
}
