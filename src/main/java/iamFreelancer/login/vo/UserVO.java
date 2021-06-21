package iamFreelancer.login.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description : ȸ�� ���� VO
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Getter
@Setter
@ToString
public class UserVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login_id;      // ���̵�
	private String login_pwd;     // �α��� ��ȣ
	private String name;
	private String email;         // �̸���
	private String phone_num;     // ��ȭ��ȣ
	private String mobile_num;    // �ڵ��� ��ȣ
	private String addr;          // �ּ�
	private String post_num;      // ���� ��ȣ
	private String user_type;     // ���:C ����:I 
	private String bank;          // ����
	private String bank_account;       // ����
	private String regist_route;  // ���԰��
	private String user_kind;     // ������:S �Ƿ���:C
	
	private String role_id;
}
