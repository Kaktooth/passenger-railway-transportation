package com.team.passengerrailwaytransportation.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {

  private final JavaMailSender mailSender;
  @Value("${spring.mail.username}")
  private String from;

  public OperatorService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendMessage(String message, String subject, String to) throws MessagingException {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

      helper.setFrom(from);
      helper.setSubject(subject);
      helper.setTo(to);
      mimeMessage.setContent(message, "text/html");
      mailSender.send(mimeMessage);
  }
}
