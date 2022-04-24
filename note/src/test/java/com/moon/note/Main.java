package com.moon.note;

import com.moon.note.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
public class Main {

    @Resource
    MailService mailService;

    @Test
    void test() throws MessagingException {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(mailService.getTransport().hashCode());
//        }
    }
}
