package org.example.ports.spi;

import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;

import java.util.List;

public interface NodePersistencePort {

  List<NodeDto> getNodes();

  NodeDto getNodesByName(String nodeName);

  NodeDto add(NodeDto nodeDto);

  NodeDto addUsage(NodeDto nodeDto, NodeUsagesDto nodeUsagesDto);


}
