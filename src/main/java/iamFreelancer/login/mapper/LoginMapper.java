package iamFreelancer.login.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import iamFreelancer.login.vo.UserVO;

@Repository
public interface LoginMapper {
	ArrayList<UserVO> findByLoginId(@Param("login_id") String id);
//	UserVO findByLoginId(@Param("login_id") String id);
	
	int memberSave(UserVO userVO);
	
	int memberRoleSave(@Param("login_id") String loginId, @Param("role_id") String roleId);
	
	int existYnByLoginId(UserVO userVO);
	
}
