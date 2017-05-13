package lq.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lq.core.dao.UserDao;
import lq.core.domain.bo.UserBO;
import lq.core.domain.bo.enums.UserStatus;
import lq.core.domain.po.converter.UserPOconvertor;
import lq.core.service.UserCoreService;



/**
 * 数据持久层业务接口实现类：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:39:34
 */
@Service
public class UserCoreServiceImp implements UserCoreService {

	@Autowired  
    private UserDao userDao; 
	
	@Override
	public List<UserBO> queryAll() {
		return UserPOconvertor.poToBo(userDao.queryAll());
	}

	@Override
	public UserBO getById(long id) {
		return UserPOconvertor.poToBo(userDao.getById(id));
	}

	@Override
	public boolean insert(UserBO bo) {
		return userDao.insert(UserPOconvertor.boToPo(bo));
	}

	@Override
	public boolean delete(long id) {
		return userDao.delete(id);
	} 
	
	@Override
	public boolean updateStatusById(long id, UserStatus status) {
		return userDao.updateStatusById(id, status);
	}
	
}
