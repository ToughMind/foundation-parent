package lq.java.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import lq.java.domain.vo.UserVO;
import lq.java.service.UserJavaService;

/**
 * 跳转控制器：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午9:40:43
 */
@Controller
public class UserController {
	
	@Resource
	private UserJavaService userJavaService;

	public void getById() { 
		UserVO vo = userJavaService.getById(1); 
		System.out.println(JSON.toJSONString(vo));
	}
}
