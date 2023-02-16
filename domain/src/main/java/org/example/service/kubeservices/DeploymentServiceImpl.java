package org.example.service.kubeservices;

import org.example.module.kube.DeploymentDto;
import org.example.module.usermodels.MemberDto;
import org.example.ports.api.DeploymentServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.KubeDeploymentsPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DeploymentServiceImpl implements DeploymentServicePort {

  private Logger LOGGER = LoggerFactory.getLogger(DeploymentServiceImpl.class);

  private KubeDeploymentsPersistencePort deploymentsPersistencePort;

  private MemberPersistencePort memberPersistencePort;

  private MailPersistencePort mailPersistencePort;


  public DeploymentServiceImpl(KubeDeploymentsPersistencePort deploymentsPersistencePort, MemberPersistencePort memberPersistencePort, MailPersistencePort mailPersistencePort) {
    this.deploymentsPersistencePort = deploymentsPersistencePort;
    this.memberPersistencePort = memberPersistencePort;
    this.mailPersistencePort = mailPersistencePort;
  }

  @Override
  public List<DeploymentDto> getDeployments() throws IOException {
    return this.deploymentsPersistencePort.getDeployments();
  }

  @Override
  public boolean createScallingRequst(String name, String namespce, int replicas) {
    List< MemberDto> memberDtoList = this.memberPersistencePort.getAllMembers();
    String sub ="Scalling Deployment : "+name;
    String message= "A new Scalling job is added\nScalling "+name+"\nDate: "+new Date().toString()+"\nRelicas set to "+replicas;
    for (MemberDto memberdto:memberDtoList) {
      this.mailPersistencePort.sendEmail(memberdto.getEmail(),sub,message);
    }
    return this.deploymentsPersistencePort.createScallingRequst(name, namespce,replicas);
  }
}
