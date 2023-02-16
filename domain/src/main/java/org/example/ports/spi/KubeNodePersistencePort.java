package org.example.ports.spi;

import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;

import java.util.List;

public interface KubeNodePersistencePort {
  List<NodeDto> getNodes();

  NodeDto getNodesId(String nodeName);

  NodeUsagesDto getUsage(String nodeName);

}
