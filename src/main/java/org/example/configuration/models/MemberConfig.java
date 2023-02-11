package org.example.configuration.models;

import org.example.adapter.MailAdapter;
import org.example.adapters.MemberJpaAdapter;
import org.example.ports.api.UserServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.example.service.userservices.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {

  @Bean
  public MemberPersistencePort memberPersistence(){
    return new MemberJpaAdapter();
  }
  @Bean
  public MailPersistencePort mailPersistence(){
    return new MailAdapter();
  }

  @Bean
  public UserServicePort memberService(){
    return new UserServiceImpl(memberPersistence(), mailPersistence());
  }


}
