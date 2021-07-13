package iamFreelancer.core.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import iamFreelancer.login.util.LoginUserDetailHelper;
import iamFreelancer.login.vo.UserVO;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Autowired
	public LoginUserDetailHelper loginUserDetailHelper;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
         * �ּ��� ������ ������ ���� ��� Ư��������(login������)�� �̵� �ϴ� ����
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
        
		UserVO userVO = loginUserDetailHelper.getAuthenticatedUser();
		
		// ������ ���� ��û�� URL�� '/main'�� ��� ��û �������� �̵�
		if ("[ROLE_ANONYMOUS]".equals(userVO.getRole_id()) && "/main".equals(request.getRequestURI())) {
			return true; // true�� ��û�� URL�� controller�� �̵�
		}
		
		// ��û�� URL�� ������ ���� �� '/main'���� �̵���Ű�� ����
		// - ������ ����
		if ("[ROLE_ANONYMOUS]".equals(userVO.getRole_id()) && "/pro/moveMenu/myPage/mypage_myinfo".equals(request.getRequestURI())) {
			response.sendRedirect("/main");
			return false; // ��û�� URL�� controller�� �̵� �Ұ�
		}
		
        /*������ ��� ��û�� �������� �̵�*/
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
