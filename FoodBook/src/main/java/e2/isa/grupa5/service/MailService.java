package e2.isa.grupa5.service;

import e2.isa.grupa5.model.users.SystemManager;
import e2.isa.grupa5.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Viktor on 12/16/2016.
 */

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(@Autowired JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(User user) throws MailException{

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("panel@reservation4.me");
        simpleMailMessage.setSubject("Registration");

        String mailContent = "Thank You for registering on FoodBook! Please follow this link to confirm your registration : localhost:8080/#/confirmRegistration \n\n";


        System.out.println("BEFORE SENDING TO "+user.getEmail());

        System.out.println(javaMailSender.toString());

        simpleMailMessage.setText(mailContent);
        javaMailSender.send(simpleMailMessage);

        System.out.println("AFTER SENDING TO "+user.getEmail());
    }
}