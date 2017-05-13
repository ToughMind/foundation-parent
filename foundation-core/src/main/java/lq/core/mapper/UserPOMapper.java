package lq.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lq.core.domain.bo.enums.UserStatus;
import lq.core.domain.po.UserPO;


/**
 * 数据持久层Mapper映射接口：用户。
 * 
 * @author 刘泉
 * @date 2016年9月29日 下午3:47:39
 */
public interface UserPOMapper {
	
	List<UserPO> queryAll();

	UserPO getById(long id);

	boolean insert(UserPO po);

	boolean delete(long id);

	boolean updateStatusById(@Param("id")long id, @Param("status")UserStatus status);
}
