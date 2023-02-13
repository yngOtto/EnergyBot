package org.example.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NodeUsages implements Serializable {
  private Long id;

  private LocalDateTime createdAt;
  private BigDecimal cpu;
  private BigDecimal memory;
  private Node node;

  public NodeUsages() {
    this.id = 0L;
    this.createdAt = null;
    this.node = new Node();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public BigDecimal getCpu() {
    return cpu;
  }

  public void setCpu(BigDecimal cpu) {
    this.cpu = cpu;
  }

  public BigDecimal getMemory() {
    return memory;
  }

  public void setMemory(BigDecimal memory) {
    this.memory = memory;
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }
}
