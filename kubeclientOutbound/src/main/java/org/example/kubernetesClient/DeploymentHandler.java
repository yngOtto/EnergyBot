package org.example.kubernetesClient;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1DeploymentSpec;
import io.kubernetes.client.util.Config;
import org.example.config.ApiServiceClientFile;
import org.example.entity.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeploymentHandler {
  private static Logger LOGGER = LoggerFactory.getLogger(DeploymentHandler.class);

  private static final String DEFAULT_NAME_SPACE = "default";

  public List<Deployment> getDeployments() throws IOException, ApiException {
    List<Deployment> deploymentList = new ArrayList<>();
    AppsV1Api appsV1Api = new AppsV1Api();
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    CoreV1Api COREV1_API = new CoreV1Api();
    appsV1Api.setApiClient(COREV1_API.getApiClient());
    V1DeploymentList listNamespacedDeployment =
            appsV1Api.listNamespacedDeployment(
                    DEFAULT_NAME_SPACE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Boolean.FALSE);
    List<V1Deployment> appsV1DeploymentItems = listNamespacedDeployment.getItems();
    for (V1Deployment deployment: appsV1DeploymentItems) {
      Deployment newdeployment = new Deployment();
      newdeployment.setName(deployment.getMetadata().getName());
      newdeployment.setReplicas(deployment.getStatus().getReplicas());
      newdeployment.setImageName(deployment.getSpec().getTemplate().getSpec().getContainers().get(0).getImage());
      newdeployment.setNameSpace(deployment.getMetadata().getNamespace());
      if(deployment.getStatus().getReplicas() == null){
        newdeployment.setReplicas(0);
      }else{
        newdeployment.setReplicas(deployment.getStatus().getReplicas());
      }
      if( deployment.getStatus().getUnavailableReplicas() == null){
        newdeployment.setUnavailableReplicas(0);
      }else{
        newdeployment.setUnavailableReplicas(deployment.getStatus().getUnavailableReplicas());
      }
      if( deployment.getStatus().getUpdatedReplicas() == null){
        newdeployment.setUpdatedReplicas(0);
      }else{
        newdeployment.setUpdatedReplicas(deployment.getStatus().getUpdatedReplicas());
      }
      if( deployment.getStatus().getReadyReplicas() == null){
        newdeployment.setReadyreplicas(0);
      }else{
        newdeployment.setReadyreplicas(deployment.getStatus().getReadyReplicas());
      }
      deploymentList.add(newdeployment);
    }
    return deploymentList;
  }


  public boolean scallingDeployment(String name, String namespce, int replicas) throws ApiException, IOException {
    AtomicBoolean done = new AtomicBoolean(false);
    AppsV1Api appsV1Api = new AppsV1Api();
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    CoreV1Api COREV1_API = new CoreV1Api();
    appsV1Api.setApiClient(COREV1_API.getApiClient());
    V1DeploymentList listNamespacedDeployment =
            appsV1Api.listNamespacedDeployment(
                    DEFAULT_NAME_SPACE,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Boolean.FALSE);

    List<V1Deployment> appsV1DeploymentItems = listNamespacedDeployment.getItems();
    Optional<V1Deployment> findedDeployment =
            appsV1DeploymentItems.stream()
                    .filter(
                            (V1Deployment deployment) ->
                                    deployment.getMetadata().getName().equals(name))
                    .findFirst();
    findedDeployment.ifPresent(
            (V1Deployment deploy) -> {
              try {
                V1DeploymentSpec newSpec = deploy.getSpec().replicas(replicas);
                V1Deployment newDeploy = deploy.spec(newSpec);
                appsV1Api.replaceNamespacedDeployment(
                        name, DEFAULT_NAME_SPACE, newDeploy, null, null, null, null);
                done.set(true);
              } catch (ApiException ex) {
                done.set(false);

              }

            });
    return done.get();
  }
}
