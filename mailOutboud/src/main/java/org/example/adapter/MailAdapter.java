package org.example.adapter;

import org.example.ports.spi.MailPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailAdapter implements MailPersistencePort   {

  private Logger LOGGER = LoggerFactory.getLogger(MailAdapter.class);

  @Autowired
  private JavaMailSender emailSender;

  @Override
  public void sendEmail(String mail,String subject, String messaget) {
    try {
      MimeMessage mimeMessage = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(messaget, true);
      helper.setTo(mail);
      helper.setSubject(subject);
      helper.setFrom("bachelorprojeekt@vittech.dk");
      LOGGER.info("Sending email to "+mail,mimeMessage);
      emailSender.send(mimeMessage);
      LOGGER.info("Sent email to "+mail,mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("unable to send email", e);
      throw new IllegalStateException("unable to send email");
    }
  }
}
