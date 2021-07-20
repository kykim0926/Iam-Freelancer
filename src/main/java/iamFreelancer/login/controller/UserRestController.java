package iamFreelancer.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iamFreelancer.login.service.LoginService;
import iamFreelancer.login.vo.UserVO;

/**
 * @description : 회원 가입 controller
 * @author Koreasoft kykim
 * @version : 1.0
 */

@RestController
public class UserRestController {
	@Autowired
	private LoginService loginService;
	
	/**
	 * 회원가입
	 * @param userVO
	 * @return
	 */
	@PostMapping("/member/regist")
	public String saveUserInfo(@RequestBody UserVO userVO) {
		return loginService.insertUser(userVO);
	}
	
	/**
	 * 아이디 존재 여부
	 * @param userVO
	 * @return
	 */
	@PostMapping("/member/existChk")
	public boolean existYnByLoginId(@RequestBody UserVO userVO) {
		return loginService.existYnByLoginId(userVO);
	}
	
	/**
	 * 회원 정보 update
	 * @param userVO
	 * @return
	 */
	@PostMapping("/member/memberInfoUpdate")
	public String memberInfoUpdate(@RequestBody UserVO userVO) {
		return loginService.memberInfoUpdate(userVO);
	}
	
	/**
	 * 로그인 비밀번호 update
	 * @param userVO
	 * @return
	 */
	@PostMapping("/member/memberLoginPwdUpdate")
	public String memberLoginPwdUpdate(@RequestBody UserVO userVO) {
		return loginService.memberLoginPwdUpdate(userVO);
	}
	
	/**
	 * 이름과 이메일로 로그인 아이디 조회
	 * @param userVO
	 * @return
	 */
	@PostMapping(value="/login/findIdByNameAndEmail")
	public String findIdByNameAndEmail(@RequestBody UserVO userVO, BindingResult result) {
		if (!result.hasErrors()) {
			return loginService.findIdByNameAndEmail(userVO);			
		} else {
			return "param error";
		}
	}
}
