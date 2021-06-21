package iamFreelancer.SMS.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

/**
 * @description : 핸드폰 번호 및 인증번호
 * @author Koreasoft kykim
 * @version : 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsCertificationRequest {

    private String mobile_num;
    private String certificationNumber;

    @Builder
    public SmsCertificationRequest(String mobile_num, String certificationNumber) {
        this.mobile_num = mobile_num;
        this.certificationNumber = certificationNumber;
    }

}
