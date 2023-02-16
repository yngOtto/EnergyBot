package org.example.service.kubeservices;

import org.example.module.kube.NodeDto;

import org.example.module.kube.NodeUsagesDto;
import org.example.ports.api.NodeServicePort;
import org.example.ports.spi.NodePersistencePort;
import org.example.ports.spi.KubeNodePersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NodeServiceImpl implements NodeServicePort {

  private Logger LOGGER = LoggerFactory.getLogger(NodeServiceImpl.class);
  private NodePersistencePort nodePersistencePort;
  private KubeNodePersistencePort kubeNodePersistencePort;

  public NodeServiceImpl(NodePersistencePort nodePersistencePort, KubeNodePersistencePort kubeNodePersistencePort) {
    this.nodePersistencePort = nodePersistencePort;
    this.kubeNodePersistencePort = kubeNodePersistencePort;
  }


  @Override
  public List<NodeDto> findNodes() {
    List<NodeDto> nodeDtoList = kubeNodePersistencePort.getNodes();
    for (NodeDto nodeDto:nodeDtoList) {
      if(nodePersistencePort.getNodesByName(nodeDto.getName())==null){
          nodePersistencePort.add(nodeDto);
      }
    }
    return nodePersistencePort.getNodes();
  }

  @Override
  public List<NodeDto> getNodes() {
    return nodePersistencePort.getNodes();
  }

  @Override
  public NodeDto getdbNodesByName(String name) {
    return nodePersistencePort.getNodesByName(name);
  }

  @Override
  public NodeDto Update(NodeDto nodeDto) {
    NodeUsagesDto nodeUsagesDto = kubeNodePersistencePort.getUsage(nodeDto.getName());
    return nodePersistencePort.addUsage(nodeDto, nodeUsagesDto);
  }
}
