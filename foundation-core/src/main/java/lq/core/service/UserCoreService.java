package lq.core.service;

import java.util.List;

import lq.core.domain.bo.UserBO;
import lq.core.domain.bo.enums.UserStatus;

/**
 * 数据持久层业务接口：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:37:46
 */
public interface UserCoreService {
	
	List<UserBO> queryAll();

	UserBO getById(long id);

	boolean insert(UserBO bo);

	boolean delete(long id);

	public boolean updateStatusById(long id, UserStatus status);
}
