package lq.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lq.core.domain.bo.enums.UserStatus;
import lq.core.domain.po.UserPO;
import lq.core.mapper.UserPOMapper;


/**
 * 数据持久层DAO：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:35:28
 */
@Repository
public class UserDao {

	@Autowired
	private UserPOMapper userPOMapper;

	public List<UserPO> queryAll() {
		return userPOMapper.queryAll();
	}

	public UserPO getById(long id) {
		return userPOMapper.getById(id);
	}

	public boolean insert(UserPO po) {
		return userPOMapper.insert(po);
	}

	public boolean delete(long id) {
		return userPOMapper.delete(id);
	}
	
	public boolean updateStatusById(long id, UserStatus status) {
		return userPOMapper.updateStatusById(id, status);
	}
}
