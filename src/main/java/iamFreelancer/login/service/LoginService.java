package iamFreelancer.login.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import iamFreelancer.login.mapper.LoginMapper;
import iamFreelancer.login.vo.UserPrincipalVO;
import iamFreelancer.login.vo.UserVO;

/**
 * @description : ȸ�� ���� DBó�� �� ��ȸ
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Service
public class LoginService implements UserDetailsService{
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/* DB���� ���������� �ҷ��´�.
	 * Custom�� Userdetails Ŭ������ ���� ���ָ� �ȴ�.
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ArrayList<UserVO> userAuthes = loginMapper.findByLoginId(username);
		
		if(userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User "+username+" Not Found!");
		}
		
		return new UserPrincipalVO(userAuthes);
	}
	
	/* ȸ�� ����
	 * �ѹ� ����
	 * */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String insertUser(UserVO userVO) {
		
		userVO.setLogin_pwd(bCryptPasswordEncoder.encode(userVO.getLogin_pwd()));
		int flag = loginMapper.memberSave(userVO);		
		if (flag > 0) {
			loginMapper.memberRoleSave(userVO.getLogin_id(), userVO.getRole_id());
			return "success";
		}
		
		return "fail";
	}
	
	/*
	 * login_id �ߺ� üũ
	 */
	public boolean existYnByLoginId(UserVO userVO) {

		boolean bResult = false;
		
		int userCnt = loginMapper.existYnByLoginId(userVO);
		
		if (userCnt > 0) {
			bResult = true;
		}
		
		return bResult;
	}
}
