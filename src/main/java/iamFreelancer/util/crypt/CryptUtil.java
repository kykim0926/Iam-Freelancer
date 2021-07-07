package iamFreelancer.util.crypt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CryptUtil {
	@Autowired
	private PasswordEncoder passwordEncoderBean;
	
	private static PasswordEncoder passwordEncoder;

	@PostConstruct
	@SuppressWarnings("static-access")
	public void init() {
		this.passwordEncoder = passwordEncoderBean;
	}

	/**
	 * ������� ��й�ȣ ��ġ ����
	 * @param plainText
	 * @param cryptText
	 * @return
	 */
	public static boolean loginPwdEncryptCompare(String plainText, String cryptText) {
		boolean bCompare = false;
		log.debug("[��й�ȣ ����] ���� ��й�ȣ" + plainText);
		if (passwordEncoder.matches(plainText, cryptText)) {
			bCompare = true;
			log.debug("�������� ��ġ");
		} else {
			log.debug("�������� ����ġ");
		}
		
		return bCompare;

	}
}
