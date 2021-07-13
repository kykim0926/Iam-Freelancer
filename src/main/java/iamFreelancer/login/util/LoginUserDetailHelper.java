package iamFreelancer.login.util;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import iamFreelancer.login.mapper.LoginMapper;
import iamFreelancer.login.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @description : �α����� ������� ���� �� üũ
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Service
@Slf4j
public final class LoginUserDetailHelper {
	@Autowired
	private LoginMapper loginMapper;
    /**
     * ������ ����ڰ�ü�� VO�������� �����´�.
     * @return ����� ValueObject
     */
	public UserVO getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserVO userVO = new UserVO();
        
        if (authentication.getPrincipal() instanceof User) {
        	UserDetails details = (UserDetails) authentication.getPrincipal();
        	
        	log.debug("## LoginUserDetailsHelper.getAuthenticatedUser : AuthenticatedUser is {}", details.getUsername());

			userVO = loginMapper.findByLoginId(details.getUsername());
	        return userVO;
        } else {
        	Collection<? extends GrantedAuthority> authRole= authentication.getAuthorities();
        	String userRole = authRole.toString();
        	userVO.setRole_id(userRole);
        	
        	log.debug("## LoginUserDetailsHelper.getAuthenticatedUser : AuthenticatedUser is {}", userRole);
        	
        	return userVO;
        }
    }
}
