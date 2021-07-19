package iamFreelancer.mail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iamFreelancer.login.vo.UserVO;
import iamFreelancer.mail.service.MailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/mailSend")
    public void mailSend(UserVO userVO) {
        mailService.mailSend(userVO);
    }
}

