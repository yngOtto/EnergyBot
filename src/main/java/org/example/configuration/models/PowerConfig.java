package org.example.configuration.models;

import org.example.adapter.MailAdapter;
import org.example.adapter.PowerAdapter;
import org.example.adapters.KubeDeploymentAdapter;
import org.example.adapters.MemberJpaAdapter;
import org.example.ports.api.PowerServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.PowerPersistancePort;
import org.example.ports.spi.KubeDeploymentsPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.example.service.kubeservices.PowerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerConfig {

    @Bean
    public PowerPersistancePort powerPersistance(){return new PowerAdapter();}

    @Bean
    public KubeDeploymentsPersistencePort deploymentsForJobPersistence(){return new KubeDeploymentAdapter();}

    @Bean
    public MemberPersistencePort memberForbotPersistence(){
        return new MemberJpaAdapter();
    }
    @Bean
    public MailPersistencePort mailforBotPersistence(){
        return new MailAdapter();
    }


    // Pode Service ports
    @Bean
    public PowerServicePort powerServicePort () {
        return new PowerServiceImpl(powerPersistance(),memberForbotPersistence(),mailforBotPersistence(),deploymentsForJobPersistence());
    }
}
