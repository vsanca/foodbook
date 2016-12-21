package e2.isa.grupa5.service;

import e2.isa.grupa5.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Viktor on 12/16/2016.
 */

@Service
public class MailManager {

    private JavaMailSender javaMailSender;

    @Autowired
    public void setMailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(User user) throws MailException{
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("panel@reservation4.me");
        simpleMailMessage.setSubject("Registration");

        String mailContent = "Thank You for registering on FoodBook! Please follow this link to confirm your registration : localhost:8080/#/confirm \n\n";

        simpleMailMessage.setText(mailContent);
        javaMailSender.send(simpleMailMessage);
    }
}
