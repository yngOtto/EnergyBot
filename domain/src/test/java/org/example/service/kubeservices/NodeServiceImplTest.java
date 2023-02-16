package org.example.service.kubeservices;

import org.example.module.kube.NodeDto;
import org.example.ports.spi.NodePersistencePort;
import org.example.ports.spi.KubeNodePersistencePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class NodeServiceImplTest {



  NodeServiceImpl nodeService;

  @Mock NodePersistencePort nodePersistencePort;

  @Mock KubeNodePersistencePort kubeNodePersistencePort;



  @BeforeEach
  void setUp() {
    kubeNodePersistencePort.getNodes().add(new NodeDto());
    nodeService = new NodeServiceImpl(nodePersistencePort, kubeNodePersistencePort);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findNodes() {
    List<NodeDto> nodeDtoList=this.nodeService.findNodes();
  }

  @Test
  void getNodes() {
  }

  @Test
  void getdbNodesByName() {
  }

  @Test
  void update() {
  }
}