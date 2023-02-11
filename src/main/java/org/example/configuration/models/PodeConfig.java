package org.example.configuration.models;

import org.example.adapters.KubePodeAdapter;
import org.example.adapters.PodeJpaAdapter;
import org.example.ports.api.PodeServicePort;
import org.example.ports.spi.PodePersistancePort;
import org.example.ports.spi.KubePodePersistancePort;
import org.example.service.kubeservices.PodeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PodeConfig {
  @Bean
  public PodePersistancePort podePersistence(){return new PodeJpaAdapter();}


  @Bean
  public KubePodePersistancePort KubePodePersistence(){return new KubePodeAdapter();}


  // Pode Service ports
  @Bean
  public PodeServicePort cronePodeService () {
    return new PodeServiceImpl(podePersistence(), KubePodePersistence());
  }
}
