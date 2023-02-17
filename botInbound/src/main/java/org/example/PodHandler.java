package org.example;

import org.example.module.kube.NodeDto;
import org.example.ports.api.NodeServicePort;
import org.example.ports.api.PodeServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PodHandler {

  private Logger LOGGER = LoggerFactory.getLogger(NodeHandler.class);
  @Autowired
  private PodeServicePort podeServicePort;


  @Scheduled (fixedRate = 60000)
  public void findNodes(){
    LOGGER.info("[FInding Pods ] : Starting..");
    podeServicePort.getPodes();
  }
  @Scheduled (fixedRate = 10000)
  public void updateNodeUsageDB() {
    LOGGER.info("[Updating Node Usage to DB] : Starting..");
  }

}
