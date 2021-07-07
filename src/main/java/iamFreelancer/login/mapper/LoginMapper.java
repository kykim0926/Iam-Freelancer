package iamFreelancer.login.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import iamFreelancer.login.vo.UserVO;

@Repository
public interface LoginMapper {
	UserVO findByLoginId(@Param("login_id") String id);
	
	int memberSave(UserVO userVO);
	
	int memberRoleSave(@Param("login_id") String loginId, @Param("role_id") String roleId);
	
	int existYnByLoginId(UserVO userVO);
	
	int memberInfoUpdate(UserVO userVO);
	
	int memberLoginPwdUpdate(UserVO userVO);
}
