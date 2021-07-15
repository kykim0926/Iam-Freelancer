package iamFreelancer.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import iamFreelancer.login.util.LoginUserDetailHelper;
import iamFreelancer.login.vo.UserVO;

/**
 * @description : �α��ΰ� ȸ�� ���� ó��
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Controller
public class LoginController {
	// TODO:�α��� ���� html�������� login������ �̵���Ű�� �ҽ� ������ ��
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
	 * �α��� ������ �̵�
	 */
	@GetMapping("loginPage")
	public String login() {
		return "pro/login";
	}
	
	/*
	 * �������
	 */
	@GetMapping("firstJoinStep")
	public String firstJoinStep() {
		return "pro/join1";
	}
	
	/*
	 * ȸ�� ���� ���� ����
	 */
	@GetMapping("joinDetailRegist")
	public String joinDetailRegist() {
		return "pro/join2";
	}
	
	/*
	 * ȸ�� ���� �Ϸ� ������
	 */
	@GetMapping("pro/join3")
	public String memberRegistComplete() {
		return "pro/join3";
	}
	
	/*
	 * �α��� ���� ������
	 * �� �ڵ尡 �־�� ID �Ǵ� ��й�ȣ�� �ùٸ��� ���� ��� �ٽ� login�������� redirect�ȴ�.
	 */
	@GetMapping("/pro/login")
	public String loginComplete(Model model) {
		model.addAttribute("exception","�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�");
		
		return "pro/login";
	}
	
	/*
	 * �α��� ID ã�� ������
	 */
	@GetMapping("/pro/login/findLoginId")
	public String findLoginId(Model model) {
		return "pro/login/findid";
	}
	
	/*
	 * �α��� Password ã�� ������
	 */
	@GetMapping("/pro/login/findLoginPw")
	public String findLoginPw(Model model) {
		return "pro/login/findpw";
	}
}

