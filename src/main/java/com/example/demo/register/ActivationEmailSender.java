package com.example.demo.register;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class ActivationEmailSender {
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public ActivationEmailSender(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public void sendActivationMessage(User user) {
        MimeMessage message = emailSender.createMimeMessage();
        String template = prepareBodyTemplate(user);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setFrom("obraskihost@o2.pl");
            mimeMessageHelper.setSubject("Activate Account");
            mimeMessageHelper.setText(template, true);

        } catch (MessagingException e) {
            System.err.println(e);
        }

        emailSender.send(message);
    }

    private String prepareBodyTemplate(User user) {
        Context context = new Context();
        context.setVariable("token", user.getToken());
        return templateEngine.process("activationMailTemplate", context);
    }


}
