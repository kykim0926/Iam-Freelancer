package iamFreelancer.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import iamFreelancer.login.util.LoginUserDetailHelper;
import iamFreelancer.login.vo.UserVO;

/**
 * @description : 로그인과 회원 가입 처리
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Controller
public class LoginController {
	// TODO:로그인 관련 html페이지는 login폴더로 이동시키고 소스 수정할 것
	@Autowired
	public LoginUserDetailHelper loginUserDetailHelper;
	
	@GetMapping("/main")
	public String freeLancerMain(ModelMap model) throws Exception{
		UserVO userVO = loginUserDetailHelper.getAuthenticatedUser();
		
		model.addAttribute("userVO", userVO);
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String loadAccessdeniedPage() throws Exception{
		return "pro/login";
	}
	
	/*
	 * 로그인 페이지 이동
	 */
	@GetMapping("loginPage")
	public String login() {
		return "pro/login";
	}
	
	/*
	 * 약관동의
	 */
	@GetMapping("firstJoinStep")
	public String firstJoinStep() {
		return "pro/join1";
	}
	
	/*
	 * 회원 가입 생세 내역
	 */
	@GetMapping("joinDetailRegist")
	public String joinDetailRegist() {
		return "pro/join2";
	}
	
	/*
	 * 회원 가입 완료 페이지
	 */
	@GetMapping("pro/join3")
	public String memberRegistComplete() {
		return "pro/join3";
	}
	
	/*
	 * 로그인 성공 페이지
	 * 이 코드가 있어야 ID 또는 비밀번호가 올바르지 않을 경우 다시 login페이지로 redirect된다.
	 */
	@GetMapping("/pro/login")
	public String loginComplete(Model model) {
		model.addAttribute("exception","가입하지 않은 아이디이거나, 잘못된 비밀번호입니다");
		
		return "pro/login";
	}
	
	/*
	 * 로그인 ID 찾기 페이지
	 */
	@GetMapping("/pro/login/findLoginId")
	public String findLoginId(Model model) {
		return "pro/login/findid";
	}
	
	/*
	 * 로그인 Password 찾기 페이지
	 */
	@GetMapping("/pro/login/findLoginPw")
	public String findLoginPw(Model model) {
		return "pro/login/findpw";
	}
}

