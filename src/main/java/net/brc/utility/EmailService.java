package net.brc.utility;

import lombok.RequiredArgsConstructor;
import net.brc.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void constructResetTokenEmail(
            String contextPath, Locale locale, String token, Reader reader, String password
    ) {

        String url = contextPath + "/newUser?token="+token;
        String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(reader.getMail());
        email.setSubject("BRC - New User");
        email.setText(url+message);
        //email.setFrom(env.getProperty("support.email"));
        //return email;
        javaMailSender.send(email);
    }
}
