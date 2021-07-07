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
	 * 사용자의 비밀번호 일치 여부
	 * @param plainText
	 * @param cryptText
	 * @return
	 */
	public static boolean loginPwdEncryptCompare(String plainText, String cryptText) {
		boolean bCompare = false;
		log.debug("[비밀번호 변경] 현재 비밀번호" + plainText);
		if (passwordEncoder.matches(plainText, cryptText)) {
			bCompare = true;
			log.debug("계정정보 일치");
		} else {
			log.debug("계정정보 불일치");
		}
		
		return bCompare;

	}
}
