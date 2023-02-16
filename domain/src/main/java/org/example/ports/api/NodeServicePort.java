package org.example.ports.api;

import org.example.module.kube.NodeDto;
import org.example.module.kube.NodeUsagesDto;

import java.util.List;

public interface NodeServicePort {

  List<NodeDto> findNodes();
  List<NodeDto> getNodes();
  NodeDto getdbNodesByName(String name);
  NodeDto Update(NodeDto nodeDto);



}
