package iamFreelancer.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import iamFreelancer.login.vo.UserVO;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
    
    @Autowired
    private TemplateEngine templateEngine;

    private static final String MAIL_TITLE = "[Iam Freelancer] ���̵� �˷��帳�ϴ�.";

    public String mailSend(UserVO userVO) {
    	try {
        	Context context = new Context();
            context.setVariable("userVO", userVO); // �Ʒ��� thymeleaf html ���ø����� ����ϱ� ���Ͽ� userVO�� set.

            // ���̵� ã�� ���� ���� html ���ø� : resource/templates/
            String process = templateEngine.process("email/findLoginIdTemplete", context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            
            helper.setSubject(MailService.MAIL_TITLE);
            helper.setText(process, true);
            helper.setTo(userVO.getEmail());
            
            javaMailSender.send(mimeMessage);
            
            return "Mail Send success";
    	} catch (MessagingException ex) {
    		System.out.println("" + ex.getMessage());
    		return "Mail Send Error";
    	}
    }
}
