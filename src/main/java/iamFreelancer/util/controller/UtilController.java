package iamFreelancer.util.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import iamFreelancer.util.string.StringUtil;

@Controller
public class UtilController {
	/**
	 * 파라미터 갯수로 난수 발생
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mobile/certNum")
	public @ResponseBody String mobileCert(ModelMap model, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		return StringUtil.getRandomSpecialEnglishNumberStr(Integer.parseInt(request.getParameter("landomLength")));
	}
}
