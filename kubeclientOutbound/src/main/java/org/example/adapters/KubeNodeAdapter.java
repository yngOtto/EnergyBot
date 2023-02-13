package org.example.adapters;
import io.kubernetes.client.openapi.ApiException;
import org.example.entity.Node;
import org.example.entity.NodeUsages;
import org.example.kubernetesClient.NodeHandler;
import org.example.mapper.NodeMapper;
import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;
import org.example.ports.spi.KubeNodePersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class KubeNodeAdapter implements KubeNodePersistencePort {

  private Logger LOGGER = LoggerFactory.getLogger(KubeNodeAdapter.class);
  private NodeHandler nodeNodeHandler = new NodeHandler();

  private NodeMapper nodeMapper = new NodeMapper();



  @Override
  public List<NodeDto> getNodes() {
    try {
      nodeMapper = new NodeMapper();
      List<NodeDto> nodeDtoList= new ArrayList<>();
      List<Node> nodeList = nodeNodeHandler.getNodes();
      for (Node node:nodeList) {
        NodeDto nodeDto = new NodeDto();
        nodeDto.setId(node.getId());
        nodeDto.setName(node.getName());
        nodeDto.setArch(node.getArch());
        nodeDto.setOs(node.getOs());
        nodeDto.setMarster(node.isMarster());
        nodeDtoList.add(nodeDto);
      }
      return nodeDtoList;
    } catch (IOException | ApiException e) {
      LOGGER.error("[Get nodes]: "+e.getMessage());
    }
    return null;
  }

  @Override
  public NodeDto getNodesId(String nodeName) {
    try {
      Node node = nodeNodeHandler.getNode(nodeName);
      nodeMapper = new NodeMapper();
      return nodeMapper.nodeToNodeDto(node);
    } catch (IOException | ApiException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public NodeUsagesDto getUsage(String nodeName) {
    try {
      NodeUsages nodeUsages = nodeNodeHandler.getNodeUsage(nodeName);
      if(nodeUsages!=null){
        NodeUsagesDto nodeUsagesDto = new NodeUsagesDto();
        nodeUsagesDto.setId(nodeUsages.getId());
        nodeUsagesDto.setCpu(nodeUsages.getCpu());
        nodeUsagesDto.setMemory(nodeUsages.getMemory());
        nodeUsagesDto.setCreatedAt(nodeUsages.getCreatedAt());
        return nodeUsagesDto;
      }
    } catch (IOException | ApiException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
