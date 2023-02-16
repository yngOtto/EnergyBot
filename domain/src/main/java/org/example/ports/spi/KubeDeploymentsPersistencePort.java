package org.example.ports.spi;

import org.example.module.kube.DeploymentDto;

import java.io.IOException;
import java.util.List;

public interface KubeDeploymentsPersistencePort {

  List<DeploymentDto> getDeployments() throws IOException;

  boolean createScallingRequst(String name, String namespce, int replicas);
}
