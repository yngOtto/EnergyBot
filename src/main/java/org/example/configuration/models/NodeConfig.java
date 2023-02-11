package org.example.configuration.models;


import org.example.adapters.KubeNodeAdapter;
import org.example.adapters.NodeJpaAdapter;
import org.example.ports.api.NodeServicePort;
import org.example.ports.spi.NodePersistencePort;;
import org.example.ports.spi.KubeNodePersistencePort;
import org.example.service.kubeservices.NodeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NodeConfig {


  // Node Persistences ports
  @Bean
  public NodePersistencePort nodePersistence(){return new NodeJpaAdapter();}


  @Bean
  public KubeNodePersistencePort KubenodePersistence(){return new KubeNodeAdapter();}


  // Node Service ports
  @Bean
  public NodeServicePort croneNodeService () {
    return new NodeServiceImpl(nodePersistence(), KubenodePersistence());
  }


}
