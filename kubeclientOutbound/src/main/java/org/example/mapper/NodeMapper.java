package org.example.mapper;


import org.example.entity.Node;
import org.example.entity.NodeUsages;
import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NodeMapper {

  private Logger LOGGER = LoggerFactory.getLogger(NodeMapper.class);

  public NodeDto nodeToNodeDto(Node node){
    NodeDto nodeDto = new NodeDto();
    nodeDto.setId(node.getId());
    nodeDto.setName(node.getName());
    nodeDto.setArch(node.getArch());
    nodeDto.setOs(node.getOs());
    nodeDto.setMarster(node.isMarster());
    nodeDto.setUsages(this.nodeUsagesListToNodeUsageDtoList(node.getUsages()));
    return nodeDto;
  }

  public  Node nodeDtoToNode(NodeDto nodeDto){
    Node node = new Node();
    node.setId(nodeDto.getId());
    node.setName(nodeDto.getName());
    node.setArch(nodeDto.getArch());
    node.setOs(nodeDto.getOs());
    node.setMarster(nodeDto.isMarster());
    node.setUsages(this.nodeUsagesDtoListToNodeUsageList(nodeDto.getUsages()));
    return node;
  }

  public NodeUsages nodeUsagesDtoToNodeUsages(NodeUsagesDto nodeUsagesDto){
    NodeUsages nodeUsages = new NodeUsages();
    nodeUsages.setId(nodeUsagesDto.getId());
    nodeUsages.setCpu(nodeUsagesDto.getCpu());
    nodeUsages.setMemory(nodeUsagesDto.getMemory());
    nodeUsages.setCreatedAt(nodeUsagesDto.getCreatedAt());
    return nodeUsages;
  }
  public NodeUsagesDto nodeUsagesToNodeUsagesDto(NodeUsages nodeUsages){
    NodeUsagesDto nodeUsagesDto = new NodeUsagesDto();
    nodeUsagesDto.setId(nodeUsages.getId());
    nodeUsagesDto.setCpu(nodeUsages.getCpu());
    nodeUsagesDto.setMemory(nodeUsages.getMemory());
    nodeUsagesDto.setCreatedAt(nodeUsages.getCreatedAt());
    return nodeUsagesDto;
  }

  public List<NodeUsages> nodeUsagesDtoListToNodeUsageList(List<NodeUsagesDto> nodeUsagesDtoList){
    List<NodeUsages> nodeUsagesList = new ArrayList<>();
    for (NodeUsagesDto n:nodeUsagesDtoList) {
      nodeUsagesList.add(this.nodeUsagesDtoToNodeUsages(n));
    }
    return nodeUsagesList;
  }

  public List<NodeUsagesDto>  nodeUsagesListToNodeUsageDtoList(List<NodeUsages> nodeUsagesList){
    List<NodeUsagesDto> nodeUsagesDtoList = new ArrayList<>();
    for (NodeUsages n:nodeUsagesList) {
      nodeUsagesDtoList.add(this.nodeUsagesToNodeUsagesDto(n));
    }
    return nodeUsagesDtoList;
  }
  public  List<NodeDto> nodelistToNodeDtoList(List<Node> nodeList){
    List<NodeDto> nodeDtoList = new ArrayList<>();
    for (Node node:nodeList) {
      nodeDtoList.add(nodeToNodeDto(node));
    }
    return nodeDtoList;
  }
}
