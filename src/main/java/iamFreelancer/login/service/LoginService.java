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
import iamFreelancer.util.crypt.CryptUtil;

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
		
		UserVO userAuthes = loginMapper.findByLoginId(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(userAuthes.getRole_id()));
		
		return new User(userAuthes.getLogin_id(), userAuthes.getLogin_pwd(), authorities);
	}
	
	/* *
	 * ȸ�� ����
	 * �ѹ� ����
	 * @param userVO
	 * @return
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
	
	/**
	 * ȸ������ ����
	 * @param userVO
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String memberInfoUpdate(UserVO userVO) {
		
		int flag = loginMapper.memberInfoUpdate(userVO);		
		if (flag > 0) {
			return "success";
		}
		
		return "fail";
	}
	
	/**
	 * login_id �ߺ� üũ
	 * @param userVO
	 * @return
	 */
	public boolean existYnByLoginId(UserVO userVO) {

		boolean bResult = false;
		
		int userCnt = loginMapper.existYnByLoginId(userVO);
		if (userCnt > 0) {
			bResult = true;
		}
		
		return bResult;
	}
	
	/**
	 * ���� ������� �α��� �н����� ����
	 * @param userVO
	 * @return
	 */
	public String memberLoginPwdUpdate(UserVO userVO) {

		UserVO userInfoVO = loginMapper.findByLoginId(userVO.getLogin_id()); // ����� ����
		userVO.setLogin_pwd(bCryptPasswordEncoder.encode(userVO.getLogin_pwd())); // �����Ϸ��� ��й�ȣ ��ȣȭ
		
		boolean bCompaperResult = CryptUtil.loginPwdEncryptCompare(userVO.getCur_login_pwd(), userInfoVO.getLogin_pwd());
		
		if (bCompaperResult) {
			int result = loginMapper.memberLoginPwdUpdate(userVO);
			if (result > 0) {
				return "sucess";	
			} else {
				return "fail";
			}
			
		} else {
			return "inconsistent";
		}
	}
	
	/**
	 * �̸��� �̸��Ϸ� �α��� ���̵� ��ȸ
	 * @param userVO
	 * @return
	 */
	public String findByNameAndEmail(UserVO userVO) {
		String loginId = loginMapper.findByNameAndEmail(userVO.getName(), userVO.getEmail());
		
		if (loginId == null || ("").equals(loginId)) {
			return "";
		} else {
			return loginId;			
		}
	}
}
