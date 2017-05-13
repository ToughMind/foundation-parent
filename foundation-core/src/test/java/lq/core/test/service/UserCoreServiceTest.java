package lq.core.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import lq.core.domain.bo.UserBO;
import lq.core.domain.bo.enums.UserStatus;
import lq.core.service.UserCoreService;
import lq.core.test.BaseTest;

public class UserCoreServiceTest extends BaseTest{
	
	@Autowired
	private UserCoreService userCoreService;
	
	//@Test
	public void queryAll() {
		List<UserBO> list = userCoreService.queryAll();
		long cur = System.currentTimeMillis();
		System.err.println(list.size());
		System.err.println(System.currentTimeMillis() - cur);
		//System.err.println(JSON.toJSONString(list));
	}
	
	//@Test
	public void getById() {
		UserBO bo = userCoreService.getById(7);
		System.err.println(JSON.toJSONString(bo));
	}
	
	//@Test
	public void insert() {
		UserBO bo = new UserBO();
		bo.setCreateTime(System.currentTimeMillis());
		bo.setMoney(100);
		bo.setName("good");
		bo.setPrice(50.0);
		bo.setStatus(UserStatus.GOLD);
		bo.setUpdateTime("2016-09-30 00:00:00");
		System.err.println("insert:" + userCoreService.insert(bo));
	}
	
	//@Test
	public void delete() {
		System.err.println("delete:" + userCoreService.delete(2));
	}
	
	//@Test
	public void update() {
		System.err.println("update:" + userCoreService.updateStatusById(7, UserStatus.SILVER));
	}
	
}
