package org.example.entity;

import java.util.List;

public class Container {

  private long id;
  private String name;
  private List<PodeUsage> podeUsage;

  public Container() {
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

  public List<PodeUsage> getPodeUsage() {
    return podeUsage;
  }

  public void setPodeUsage(List<PodeUsage> podeUsage) {
    this.podeUsage = podeUsage;
  }
}
