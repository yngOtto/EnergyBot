package org.example.configuration.models;

import org.example.adapter.MailAdapter;
import org.example.adapters.KubeDeploymentAdapter;
import org.example.adapters.MemberJpaAdapter;
import org.example.ports.api.DeploymentServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.KubeDeploymentsPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.example.service.kubeservices.DeploymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeploymentConfig {


  @Bean
  public KubeDeploymentsPersistencePort deploymentPersistence(){return new KubeDeploymentAdapter();}


  @Bean
  public MemberPersistencePort memberForDeploymentPersistence(){
    return new MemberJpaAdapter();
  }
  @Bean
  public MailPersistencePort mailforDeploymentPersistence(){
    return new MailAdapter();
  }

  // Node Service ports
  @Bean
  public DeploymentServicePort deploymentServicePortService () {
    return new DeploymentServiceImpl(deploymentPersistence(), memberForDeploymentPersistence(), mailforDeploymentPersistence());
  }
}
