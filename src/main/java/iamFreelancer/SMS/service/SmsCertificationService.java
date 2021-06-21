package iamFreelancer.SMS.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import iamFreelancer.SMS.util.SmsCertificationRequest;
import iamFreelancer.SMS.util.SmsMessageTemplate;
import iamFreelancer.util.string.StringUtil;
import iamFreelancer.SMS.dao.SmsCertificationDao;

/**
 * @description : SMS 처리 service
 * @author Koreasoft kykim
 * @version : 1.0
 */
@RequiredArgsConstructor
@Service
public class SmsCertificationService {

    private final SmsCertificationDao smsCertificationDao;

    // 인증 메세지 내용 생성
    public String makeSmsContent(String certificationNumber) {
        SmsMessageTemplate content = new SmsMessageTemplate();
        return content.builderCertificationContent(certificationNumber);
    }

    // sms로 인증번호 발송하고, 발송 정보를 세션에 저장
    public void sendSms(String phone) {
    	String randomNumber = StringUtil.getRandomSpecialEnglishNumberStr(6);
    	
        // TODO : 이 부분에 SMS발송처리 로직을 구현 필요
    	
        smsCertificationDao.createSmsCertification(phone, randomNumber);
    }

    // 입력한 인증번호가 발송되었던(세션에 저장된) 인증번호가 동일한지 확인
    public ResponseEntity<String> verifySms(SmsCertificationRequest smsRequestDto) {
        if (verifyCertNum(smsRequestDto)) {
        	return new ResponseEntity<>("fail", HttpStatus.OK);
        } else {
        	return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }

    // 인증이 완료되면 redis에 있는 데이터 삭제 
    public void deleteCertification(SmsCertificationRequest smsRequestDto) {
    	smsCertificationDao.removeSmsCertification(smsRequestDto.getMobile_num());
    }
    
    private boolean verifyCertNum(SmsCertificationRequest smsRequestDto) {
        return !(smsCertificationDao.hasKey(smsRequestDto.getMobile_num()) &&
            smsCertificationDao.getSmsCertification(smsRequestDto.getMobile_num())
                .equals(smsRequestDto.getCertificationNumber()));
    }
}