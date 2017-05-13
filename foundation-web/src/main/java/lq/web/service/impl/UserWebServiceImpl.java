package lq.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lq.core.service.UserCoreService;
import lq.web.domain.vo.UserVO;
import lq.web.domain.vo.convertor.UserVOconvertor;
import lq.web.service.UserWebService;

/**
 * web端业务接口实现类：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午2:37:46
 */
@Service
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	private UserCoreService userCoreService;

	@Override
	public List<UserVO> queryAll() {
		return UserVOconvertor.boToVo(userCoreService.queryAll());
	}

	@Override
	public UserVO getById(long id) {
		return UserVOconvertor.boToVo(userCoreService.getById(id));
	}

	@Override
	public boolean insert(UserVO vo) {
		return userCoreService.insert(UserVOconvertor.voToBo(vo));
	}

	@Override
	public boolean delete(long id) {
		return userCoreService.delete(id);
	}

}
