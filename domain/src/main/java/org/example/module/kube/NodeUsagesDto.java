package org.example.module.kube;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NodeUsagesDto {

  private Long id;
  private LocalDateTime createdAt;
  private BigDecimal cpu;
  private BigDecimal memory;



  public NodeUsagesDto() {
    this.id = 0L;
    this.createdAt = null;
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
}
