package org.example.configuration.models;

import org.example.adapters.KubeApiClientAdapter;
import org.example.ports.api.KubeApiClientServicePort;
import org.example.ports.spi.KubeApiClientPersistencePort;
import org.example.service.kubeservices.kubeApiClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubeApiServiceConfig {

  @Bean
  public KubeApiClientPersistencePort kubeapiFileServicePersistence(){return new KubeApiClientAdapter();}



  // Pode Service ports
  @Bean
  public KubeApiClientServicePort kubeapiFileServiceService () {
    return new kubeApiClientServiceImpl(kubeapiFileServicePersistence());
  }

}
