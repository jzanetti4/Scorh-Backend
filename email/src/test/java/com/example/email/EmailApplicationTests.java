package com.example.email;

import com.example.email.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class EmailApplicationTests {

    @Autowired
    private MailService mailService;
    @Test
    void contextLoads() {
    }


    @Test
    void testSendHtml() throws MessagingException {
        mailService.sendHtml("1245414819@qq.com","123");
    }

    @Test
    void testSend() throws MessagingException {
        mailService.send("1245414819@qq.com","123");
    }
}
