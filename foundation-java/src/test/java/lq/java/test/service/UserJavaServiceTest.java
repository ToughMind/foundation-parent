package lq.java.test.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import lq.java.domain.vo.UserVO;
import lq.java.service.UserJavaService;
import lq.java.test.BaseTest;


public class UserJavaServiceTest extends BaseTest {
	
	@Autowired
	private UserJavaService userJavaService;
	
	//@Test
	public void queryAll() {
		List<UserVO> list = userJavaService.queryAll();
		long cur = System.currentTimeMillis();
		System.err.println(list.size());
		System.err.println(System.currentTimeMillis() - cur);
	}
	
	//@Test
	public void getById() {
		UserVO vo = userJavaService.getById(7);
		System.err.println(JSON.toJSONString(vo));
	}
	
	//@Test
	public void insert() {
		UserVO vo = new UserVO();
		vo.setCreateTime(System.currentTimeMillis());
		vo.setMoney(100);
		vo.setName("good");
		vo.setPrice(50.0);
		vo.setStatus(1);
		vo.setUpdateTime(new Date());
		System.err.println("insert:" + userJavaService.insert(vo));
	}
	
	//@Test
	public void delete() {
		System.err.println("delete:" + userJavaService.delete(2));
	}
	 
}
