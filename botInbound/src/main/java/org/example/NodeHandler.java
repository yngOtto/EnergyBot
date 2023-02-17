package org.example;

import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;
import org.example.ports.api.NodeServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodeHandler {
  private Logger LOGGER = LoggerFactory.getLogger(NodeHandler.class);
  @Autowired
  private NodeServicePort nodeServicePort;


  @Scheduled (fixedRate = 60000)
  public void findNodes(){
    LOGGER.info("[FInding Nodes ] : Starting..");
    nodeServicePort.findNodes();
  }
  @Scheduled (fixedRate = 10000)
  public void updateNodeUsageDB(){
    LOGGER.info("[Updating Node Usage to DB] : Starting..");
    List<NodeDto> nodes = nodeServicePort.getNodes();
    for (NodeDto node:nodes) {
      LOGGER.info("[Updating] : "+node.getName());
      nodeServicePort.Update(node);
    }
  }



}
