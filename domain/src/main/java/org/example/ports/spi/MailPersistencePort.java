package org.example.ports.spi;

public interface MailPersistencePort {


  void sendEmail(String mail,String subject, String messaget);
}
