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

    public String mailSend(UserVO userVO, String mailTempletePath, String mailSubject) {
        try {
            Context context = new Context();
            context.setVariable("userVO", userVO); // 아래의 thymeleaf html 템플릿에서 사용하기 위하여 userVO를 set.

            // 아이디 찾기 메일 본문 html 템플릿 : resource/templates/
            String process = templateEngine.process(mailTempletePath, context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setSubject(mailSubject);
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
