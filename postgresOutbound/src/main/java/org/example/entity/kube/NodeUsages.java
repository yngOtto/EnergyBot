package org.example.entity.kube;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NodeUsages {
  @Id
  @Column (name = "id", nullable = false)
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column
  private LocalDateTime createdAt;
  private BigDecimal cpu;
  private BigDecimal memory;
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "node_id", nullable = false)
  private Node node;

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

  @Override
  public String toString() {
    return "NodeUsages{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", cpu=" + cpu +
            ", memory=" + memory +
            ", node=" + node +
            '}';
  }
}
