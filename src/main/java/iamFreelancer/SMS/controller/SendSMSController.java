package iamFreelancer.SMS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iamFreelancer.SMS.service.SmsCertificationService;
import iamFreelancer.SMS.util.SmsCertificationRequest;
import lombok.RequiredArgsConstructor;

/**
 * @description : SMS controller
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Controller
@RequiredArgsConstructor
@RestController
public class SendSMSController {
	private final SmsCertificationService smsCertificationService;
	
	//인증번호 발송
	@PostMapping("/linkage/sendSMS")
    public ResponseEntity<String> sendSms(@RequestBody SmsCertificationRequest smsRequestDto) {
        smsCertificationService.sendSms(smsRequestDto.getMobile_num());
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    //redis에서 인증번호 확인
    @PostMapping("/mobile/certNumConfirm")
    public ResponseEntity<String> smsVerification(@RequestBody SmsCertificationRequest smsRequestDto) {
        return smsCertificationService.verifySms(smsRequestDto);
    }
    
    // redis에서 인증번호 삭제
    @PostMapping("/mobile/certNumDelete")
    public void smsCertNumDelete(@RequestBody SmsCertificationRequest smsRequestDto) {
        smsCertificationService.deleteCertification(smsRequestDto);
    }
}
