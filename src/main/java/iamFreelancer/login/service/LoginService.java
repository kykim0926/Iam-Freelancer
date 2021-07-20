package iamFreelancer.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import iamFreelancer.mail.service.MailService;
import iamFreelancer.util.crypt.CryptUtil;
import iamFreelancer.util.string.StringUtil;

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
	
	@Autowired
	private MailService mailService;
	
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
	
	/* *
	 * 회원 저장
	 * 롤백 설정
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
	 * 회원정보 수정
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
	 * login_id 중복 체크
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
	 * 현재 사용자의 로그인 패스워드 변경
	 * @param userVO
	 * @return
	 */
	public String memberLoginPwdUpdate(UserVO userVO) {

		UserVO userInfoVO = loginMapper.findByLoginId(userVO.getLogin_id()); // 사용자 정보
		userVO.setLogin_pwd(bCryptPasswordEncoder.encode(userVO.getLogin_pwd())); // 변경하려는 비밀번호 암호화
		
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
	 * 이름과 이메일로 로그인 아이디 조회
	 * @param userVO
	 * @return
	 */
	public String findIdByNameAndEmail(UserVO userVO) {
		String loginId = loginMapper.findIdByNameAndEmail(userVO.getName(), userVO.getEmail());
		
		if (loginId == null || ("").equals(loginId)) {
			return "not Exsit User Email";
		} else {
			userVO.setLogin_id(loginId);
			return mailService.mailSend(userVO, "email/findLoginIdTemplete", "[Iam Freelancer] 아이디를 알려드립니다.");			
		}
	}
	
	/**
	 * 이름과 이메일로 로그인 아이디 조회
	 * @param userVO
	 * @return
	 */
	public String findPwdByIdAndNameAndEmail(UserVO userVO) {
//		Optional<String> optLoginId = Optional.empty();
		String loginId = loginMapper.findPwdByIdAndNameAndEmail(userVO.getLogin_id(), userVO.getName(), userVO.getEmail());
		
		if (loginId == null || ("").equals(loginId)) {
			return "not Exsit User Email";
		} else {
			String tempPwd = StringUtil.getRandomSpecialEnglishNumberStr(10); // 평문 임시 비밀번호 생성
			String tempEncryptPwd = bCryptPasswordEncoder.encode(tempPwd); // 임시 비밀번호 암호화
			
			userVO.setLogin_pwd(tempEncryptPwd); // DB update를 위하여 암호화된 임시 비밀번호로 set
			int result = loginMapper.memberLoginPwdUpdate(userVO);
			
			if (result > 0) {
				userVO.setLogin_id(loginId);
				userVO.setLogin_pwd(tempPwd);
				return mailService.mailSend(userVO, "email/findLoginPwdTemplete", "[Iam Freelancer] 임시 비밀번호를 알려드립니다.");
			} else {
				return "Mail Send Error";
			}
		}
	}
}
