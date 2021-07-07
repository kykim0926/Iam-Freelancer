package iamFreelancer.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import iamFreelancer.login.util.LoginUserDetailHelper;
import iamFreelancer.login.vo.UserVO;

@Controller
public class MainPageController {
	
	@Autowired
	public LoginUserDetailHelper loginUserDetailHelper;
	
	/**
	 * 개인 정보
	 * @param model
	 * @return
	 */
	@GetMapping("/pro/myPage")
	public String memberMyPage(Model model) {
		UserVO userVO = loginUserDetailHelper.getAuthenticatedUser();
		
		return "/pro/myPage/mypage_myinfo";
	}
	
	/**
	 * 메인 상단의 메뉴
	 * @return
	 */
	@GetMapping("/pro/mainTopMenu")
	public String mainHeader() {
		return "/pro/main/topMainHeader";
	}
	
	/**
	 * top menu 이동
	 * @param model
	 * @return
	 */
//	@GetMapping("/pro/moveMenu/{subPage}/{detailPage}")
//	public String moveMainMenu(@PathVariable("subPage") String subPage, @PathVariable("detailPage") String detailPage, Model model) {
//		return "/pro/" + subPage + "/" + detailPage;
//	}
	
	/**
	 * 마이페이지 이동
	 * @param model
	 * @return
	 */
	@GetMapping("/pro/moveMenu/myPage/mypage_myinfo")
	public String moveMainMenu(@RequestParam("param") String param, Model model) {
		UserVO userVO = loginUserDetailHelper.getAuthenticatedUser();
		
		model.addAttribute("userVO", userVO);
		
		return "/pro/myPage/mypage_myinfo";
	}
}
