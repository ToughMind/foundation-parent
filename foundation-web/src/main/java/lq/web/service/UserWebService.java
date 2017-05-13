package lq.web.service;

import java.util.List;

import lq.web.domain.vo.UserVO;

/**
 * web端业务接口：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午4:27:23
 */
public interface UserWebService {

	List<UserVO> queryAll();

	UserVO getById(long id);

	boolean insert(UserVO vo);

	boolean delete(long id);
}
