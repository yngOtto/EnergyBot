package org.example.entity;

import java.util.List;
import java.util.UUID;

public class Pode {

  private long id;

  private String name;

  private UUID podeUUId;

  private String nameSpace;

  private String creationTimestamp;

  private List<Container> containers;

  public Pode() {
  }

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

  public List<Container> getContainers() {
    return containers;
  }

  public void setContainers(List<Container> containers) {
    this.containers = containers;
  }
}
