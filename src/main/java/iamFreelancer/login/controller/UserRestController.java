package iamFreelancer.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/member/regist")
	public String saveUserInfo(@RequestBody UserVO userVO) {
		return loginService.insertUser(userVO);
	}
	
	@PostMapping("/member/existChk")
	public boolean existYnByLoginId(@RequestBody UserVO userVO) {
		return loginService.existYnByLoginId(userVO);
	}
}
