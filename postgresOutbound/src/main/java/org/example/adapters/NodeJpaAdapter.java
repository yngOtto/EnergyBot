package org.example.adapters;

import org.example.entity.kube.Node;
import org.example.entity.kube.NodeUsages;
import org.example.mapper.NodeDataMapper;
import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;
import org.example.ports.spi.NodePersistencePort;
import org.example.repositorys.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeJpaAdapter implements NodePersistencePort {

  private Logger LOGGER = LoggerFactory.getLogger(NodeJpaAdapter.class);
  @Autowired
  private NodeRepository nodeRepository;

  private NodeDataMapper nodeDataMapper;

  @Override
  public List<NodeDto> getNodes() {
    List<Node> nodeList = nodeRepository.findAll();
    List<NodeDto> nodeDtoList = new ArrayList<>();
    for (Node node:nodeList) {
      NodeDto nodeDto = new NodeDto();
      nodeDto.setId(node.getId());
      nodeDto.setName(node.getName());
      nodeDto.setArch(node.getArch());
      nodeDto.setOs(node.getOs());
      nodeDto.setMarster(node.isMarster());
      if(!node.getUsages().isEmpty()){
        for (NodeUsages nu: node.getUsages()) {
          NodeUsagesDto nodeUsagesDto = new NodeUsagesDto();
          nodeUsagesDto.setId(nu.getId());
          nodeUsagesDto.setCpu(nu.getCpu());
          nodeUsagesDto.setMemory(nu.getMemory());
          nodeUsagesDto.setCreatedAt(nu.getCreatedAt());
          nodeDto.getUsages().add(nodeUsagesDto);

        }
      }
      nodeDtoList.add(nodeDto);
    }
    return nodeDtoList;

  }

  @Override
  public NodeDto getNodesByName(String nodeName) {
    Node node = nodeRepository.findByName(nodeName);
    if(node==null){
      LOGGER.error("No node found");
      return null;
    }
    NodeDto nodeDto = new NodeDto();
    nodeDto.setId(node.getId());
    nodeDto.setName(node.getName());
    nodeDto.setArch(node.getArch());
    nodeDto.setOs(node.getOs());
    nodeDto.setMarster(node.isMarster());
    if(!node.getUsages().isEmpty()) {
      for (NodeUsages nu : node.getUsages()) {
        NodeUsagesDto nodeUsagesDto = new NodeUsagesDto();
        nodeUsagesDto.setId(nu.getId());
        nodeUsagesDto.setCpu(nu.getCpu());
        nodeUsagesDto.setMemory(nu.getMemory());
        nodeUsagesDto.setCreatedAt(nu.getCreatedAt());
        nodeDto.getUsages().add(nodeUsagesDto);
      }
    }
    return nodeDto;
  }

  @Override
  public NodeDto add(NodeDto nodeDto) {
    LOGGER.info("Adding nodes with name"+nodeDto.getName());
    nodeDataMapper = new NodeDataMapper();
    if(!nodeRepository.existsByName(nodeDto.getName())){
        nodeRepository.save(nodeDataMapper.nodeDtoToNode(nodeDto));
    }
    return nodeDto;
  }

  @Override
  public NodeDto addUsage(NodeDto nodeDto, NodeUsagesDto nodeUsagesDto) {
    Node node = nodeRepository.findByName(nodeDto.getName());
    if(node==null){
        LOGGER.error("No node found by name :"+nodeDto.getName());
        return null;

    }
    NodeUsages nodeUsages = new NodeUsages();
    nodeUsages.setId(nodeUsagesDto.getId());
    nodeUsages.setCpu(nodeUsagesDto.getCpu());
    nodeUsages.setMemory(nodeUsagesDto.getMemory());
    nodeUsages.setCreatedAt(nodeUsagesDto.getCreatedAt());
    nodeUsages.setNode(node);
    node.getUsages().add(nodeUsages);
    Node newNode= nodeRepository.save(node);
    NodeDto nd = new NodeDto();
    nd.setId(newNode.getId());
    nd.setName(newNode.getName());
    nd.setArch(newNode.getArch());
    nd.setOs(newNode.getOs());
    nd.setMarster(newNode.isMarster());
    for (NodeUsages nu: newNode.getUsages()) {
      NodeUsagesDto nud = new NodeUsagesDto();
      nud.setId(nu.getId());
      nud.setCpu(nu.getCpu());
      nud.setMemory(nu.getMemory());
      nud.setCreatedAt(nu.getCreatedAt());
      nd.getUsages().add(nud);
    }
    return nd;
  }
}
