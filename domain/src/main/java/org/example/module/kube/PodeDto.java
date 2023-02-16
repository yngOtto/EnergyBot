package org.example.module.kube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PodeDto {

  private long id;

  private String name;

  private UUID podeUUId;

  private String nameSpace;

  private String creationTimestamp;

  private List<ContainerDto> containers;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getPodeUUId() {
    return podeUUId;
  }

  public void setPodeUUId(UUID podeUUId) {
    this.podeUUId = podeUUId;
  }

  public String getNameSpace() {
    return nameSpace;
  }

  public void setNameSpace(String nameSpace) {
    this.nameSpace = nameSpace;
  }

  public String getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(String creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public List<ContainerDto> getContainers() {
    return containers;
  }

  public void setContainers(List<ContainerDto> containers) {
    this.containers = containers;
  }
}
