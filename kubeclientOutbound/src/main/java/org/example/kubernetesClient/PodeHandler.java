package org.example.kubernetesClient;

import io.kubernetes.client.Metrics;
import io.kubernetes.client.custom.ContainerMetrics;
import io.kubernetes.client.custom.PodMetrics;
import io.kubernetes.client.custom.PodMetricsList;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.Config;
import org.example.config.ApiServiceClientFile;
import org.example.entity.Container;
import org.example.entity.Pode;
import org.example.entity.PodeUsage;
import org.example.module.kube.PodeUsageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PodeHandler {


  private Logger LOGGER = LoggerFactory.getLogger(PodeHandler.class);
  public List<Pode> getpods(String namespace) throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
      Metrics metrics = new Metrics(client.getClient());
      PodMetricsList list = metrics.getPodMetrics("default");
      List<Pode> pods = new ArrayList<>();
      for (PodMetrics item : list.getItems()) {
          Pode p = new Pode();
          p.setName(item.getMetadata().getName());
          p.setPodeUUId(UUID.fromString(item.getMetadata().getUid()));
          p.setNameSpace(item.getMetadata().getNamespace());
          pods.add(new Pode());
      }
      return pods;
  }
  public Pode addContainer(Pode pode) throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    Metrics metrics = new Metrics(client.getClient());
    PodMetricsList list = metrics.getPodMetrics(pode.getNameSpace());
    boolean foundConatiner=false;
    for (PodMetrics item : list.getItems()) {
     if(pode.getName().equals(item.getMetadata().getName())){
       for (ContainerMetrics c : item.getContainers()) {
         for (Container podeContainer: pode.getContainers()) {
           if(c.getName().equals(podeContainer.getName())){
             foundConatiner = true;
           }
         }
         if(!foundConatiner){
           Container container = new Container();
           container.setName(c.getName());
           pode.getContainers().add(container);
           foundConatiner=false;
         }
       }
     }
    }
    return pode;
  }

  public Pode updateUsages(Pode pode ) throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    Metrics metrics = new Metrics(client.getClient());
    PodMetricsList list = metrics.getPodMetrics(pode.getNameSpace());
    boolean foundConatiner=false;
    for (PodMetrics item : list.getItems()) {
      if(pode.getName().equals(item.getMetadata().getName())){
        for (ContainerMetrics c : item.getContainers()) {
          for (Container podeContainer: pode.getContainers()) {
            if(c.getName().equals(podeContainer.getName()))
            {
              PodeUsage podeUsage  = new PodeUsage();
              podeUsage.setCpu(c.getUsage().get("cpu").getNumber());
              podeUsage.setMemory(c.getUsage().get("memory").getNumber());
              podeContainer.getPodeUsage().add(podeUsage);
            }
          }
        }
      }
    }
    return pode;
  }
  public PodeUsage ContainerUpdate(String  containername ) throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    Metrics metrics = new Metrics(client.getClient());
    PodMetricsList list = metrics.getPodMetrics(null);
    for (PodMetrics item : list.getItems()) {
      for (ContainerMetrics c : item.getContainers()) {
        {
          if (c.getName().equals(containername)) {
            PodeUsage podeUsage = new PodeUsage();
            podeUsage.setCpu(c.getUsage().get("cpu").getNumber());
            podeUsage.setMemory(c.getUsage().get("memory").getNumber());
            return podeUsage;
          }
        }
      }
    }
    return null;
  }

}
