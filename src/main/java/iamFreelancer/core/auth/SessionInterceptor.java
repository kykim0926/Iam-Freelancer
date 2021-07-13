package iamFreelancer.core.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import iamFreelancer.login.util.LoginUserDetailHelper;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Autowired
	public LoginUserDetailHelper loginUserDetailHelper;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
         * 주석된 내용은 세션이 없을 경우 특정페이지(login페이지)로 이동 하는 로직
         * 
        UserVO userVO = loginUserDetailHelper.getAuthenticatedUser();
        if ("/pro/login".equals(request.getRequestURI())) {
        	return true;
        }
        
        if ("[ROLE_ANONYMOUS]".equals(userVO.getRole_id())) {
        	response.sendRedirect("/pro/login");
        	return false;
        }
        */
        
        /*세션이 없어도 요청한 페이지로 이동*/
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, 
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, 
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
