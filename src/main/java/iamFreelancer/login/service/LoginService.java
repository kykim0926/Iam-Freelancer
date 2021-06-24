package iamFreelancer.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import iamFreelancer.login.mapper.LoginMapper;
import iamFreelancer.login.vo.UserVO;

/**
 * @description : 회원 가입 DB처리 및 조회
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Service
public class LoginService implements UserDetailsService{
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/* DB에서 유저정보를 불러온다.
	 * Custom한 Userdetails 클래스를 리턴 해주면 된다.
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO userAuthes = loginMapper.findByLoginId(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(userAuthes.getRole_id()));
		
		return new User(userAuthes.getLogin_id(), userAuthes.getLogin_pwd(), authorities);
	}
	
	/* 회원 저장
	 * 롤백 설정
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
	 * login_id 중복 체크
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
