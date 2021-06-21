package iamFreelancer.login.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description : �α��� springSecurity ó��
 * @author Koreasoft kykim
 * @version : 1.0
 */
public class UserPrincipalVO implements UserDetails{
	//UID���� ��� ������ ������ �ڹ� �����Ϸ��� �ӽ����� ���� �ο��Ѵ�.
	private static final long serialVersionUID = 1L;
	
	private ArrayList<UserVO> userVO;
	
	public UserPrincipalVO(ArrayList<UserVO> userAuthes) {
		this.userVO = userAuthes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //������ ���� �ִ� ���� ���

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(int x=0; x<userVO.size(); x++) {
			authorities.add(new SimpleGrantedAuthority(userVO.get(x).getRole_id()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() { //���� ��й�ȣ
		return userVO.get(0).getLogin_pwd();
	}

	@Override
	public String getUsername() {// ���� �̸� Ȥ�� ���̵�
		return userVO.get(0).getLogin_id();
	}
	
	@Override
	public boolean isAccountNonExpired() {// ���� ���̵� ���� �Ǿ�����
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // ���� ���̵� Lock �ɷȴ���
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //��й�ȣ�� ���� �Ǿ�����
		return true;
	}

	@Override
	public boolean isEnabled() { // ������ Ȱ��ȭ �Ǿ�����
		return true;
	}
}
