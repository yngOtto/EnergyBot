package org.example.adapters;

import io.kubernetes.client.openapi.ApiException;

import org.example.entity.Deployment;
import org.example.kubernetesClient.DeploymentHandler;
import org.example.module.kube.DeploymentDto;
import org.example.ports.spi.KubeDeploymentsPersistencePort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class KubeDeploymentAdapter implements KubeDeploymentsPersistencePort {
  private DeploymentHandler deploymentHandler = new DeploymentHandler();
  @Override
  public List<DeploymentDto> getDeployments() throws IOException {
    List<DeploymentDto> deploymentDtoList = new ArrayList<>();
    try {
      for (Deployment deployment : deploymentHandler.getDeployments()){
        DeploymentDto deploymentDto = new DeploymentDto();
        deploymentDto.setName(deployment.getName());
        deploymentDto.setImageName(deployment.getImageName());
        deploymentDto.setNameSpace(deployment.getNameSpace());
        deploymentDto.setUnavailableReplicas(deployment.getUnavailableReplicas());
        deploymentDto.setUpdatedReplicas(deployment.getUpdatedReplicas());
        deploymentDto.setReadyreplicas(deployment.getReadyreplicas());
        deploymentDto.setReplicas(deployment.getReplicas());
        deploymentDtoList.add(deploymentDto);
      }
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }
  return deploymentDtoList;
  }

  @Override
  public boolean createScallingRequst(String name, String namespce, int replicas) {
    try {
      return this.deploymentHandler.scallingDeployment(name, namespce,replicas);
    } catch (ApiException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
