package iamFreelancer.login.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description : 회원 정보 VO
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Getter
@Setter
@ToString
public class UserVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login_id;      // 아이디
	private String login_pwd;     // 로그인 암호
	private String name;
	private String email;         // 이메일
	private String phone_num;     // 전화번호
	private String mobile_num;    // 핸드폰 번호
	private String addr;          // 주소
	private String post_num;      // 우편 번호
	private String user_type;     // 기업:C 개인:I 
	private String bank;          // 은행
	private String bank_account;       // 계좌
	private String regist_route;  // 가입경로
	private String user_kind;     // 전문가:S 의뢰인:C
	
	private String role_id;
}
