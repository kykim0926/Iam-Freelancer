package iamFreelancer.SMS.util;

/**
 * @description : 인증 SMS 메세지 템플릿
 * @author Koreasoft kykim
 * @version : 1.0
 */
public class SmsMessageTemplate {
    public String builderCertificationContent(String certificationNumber) {

        StringBuilder builder = new StringBuilder();
        builder.append("[Iam Freelancer] 인증번호는");
        builder.append(certificationNumber);
        builder.append("입니다. ");

        return builder.toString();
    }

}