package org.example.kubernetesClient;

import io.kubernetes.client.Metrics;
import io.kubernetes.client.custom.NodeMetrics;
import io.kubernetes.client.custom.NodeMetricsList;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import org.example.config.ApiServiceClientFile;
import org.example.entity.Node;
import org.example.entity.NodeUsages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeHandler {

  private Logger LOGGER = LoggerFactory.getLogger(NodeHandler.class);

  public List<Node> getNodes() throws IOException, ApiException {
    List<Node> nodesList = new ArrayList<>();
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    LOGGER.info("[Connection to kubectl]: " + client.toString());
    Configuration.setDefaultApiClient(client.getClient());
    Metrics metrics = new Metrics(client.getClient());
    NodeMetricsList list = metrics.getNodeMetrics();
    for (NodeMetrics item : list.getItems()) {
      LOGGER.info("[Item from ctl add to list]: " + item.getMetadata().getLabels().toString());
      nodesList.add(this.createNode(item));
    }
    LOGGER.info("[Nodes resived from ctl]: " + nodesList.toString());
    return nodesList;
  }

  public Node getNode(String name) throws IOException, ApiException {
    ClientBuilder.cluster().build();
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());
    Metrics metrics = new Metrics(client.getClient());
    NodeMetricsList list = metrics.getNodeMetrics();
    for (NodeMetrics item : list.getItems()) {
      if (item.getMetadata().getName().equalsIgnoreCase(name)) {
        return this.createNode(item);
      }
    }
    return new Node();
  }

  private Node createNode(NodeMetrics item) {
    Node node = new Node();
    node.setName(item.getMetadata().getLabels().get("kubernetes.io/hostname"));
    node.setArch(item.getMetadata().getLabels().get("kubernetes.io/arch"));
    node.setOs(item.getMetadata().getLabels().get("beta.kubernetes.io/os"));
    node.setMarster(Boolean.parseBoolean(item.getMetadata().getLabels().get("node-role.kubernetes.io/master")));
    return node;
  }

  public NodeUsages getNodeUsage(String name) throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());

    Metrics metrics = new Metrics(client.getClient());
    NodeMetricsList list = metrics.getNodeMetrics();
    List<NodeUsages> nodeUsagesList = new ArrayList<>();
    for (NodeMetrics item : list.getItems()) {
      if (item.getMetadata().getName().equalsIgnoreCase(name)) {
        Node node = this.createNode(item);
        NodeUsages nodeUsages = new NodeUsages();
        nodeUsages.setNode(node);
        nodeUsages.setMemory(item.getUsage().get("memory").getNumber());
        nodeUsages.setCpu(item.getUsage().get("cpu").getNumber());
        nodeUsagesList.add(nodeUsages);
        return nodeUsages;
      }

    }
    return new NodeUsages();
  }

  public List<NodeUsages> getAllNodesUsages() throws IOException, ApiException {
    ApiServiceClientFile client = ApiServiceClientFile.getInstance();
    Configuration.setDefaultApiClient(client.getClient());

    Metrics metrics = new Metrics(client.getClient());
    NodeMetricsList list = metrics.getNodeMetrics();
    List<NodeUsages> nodeUsagesList = new ArrayList<>();
    for (NodeMetrics item : list.getItems()) {
      Node node = this.createNode(item);
      NodeUsages nodeUsages = new NodeUsages();
      nodeUsages.setNode(node);
      nodeUsages.setMemory(item.getUsage().get("memory").getNumber());
      nodeUsages.setCpu(item.getUsage().get("cpu").getNumber());
      nodeUsagesList.add(nodeUsages);
    }
    return nodeUsagesList;
  }

}
