package org.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {

  private Long id;
  private String name;
  private String arch;
  private String os;
  private boolean isMarster;
  private List<NodeUsages> usages;

  public Node() {
    this.id = 0L;
    this.name = "";
    this.arch = "";
    this.os = "";
    this.isMarster = false;
    this.usages = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArch() {
    return arch;
  }

  public void setArch(String arch) {
    this.arch = arch;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public boolean isMarster() {
    return isMarster;
  }

  public void setMarster(boolean marster) {
    isMarster = marster;
  }

  public List<NodeUsages> getUsages() {
    return usages;
  }

  public void setUsages(List<NodeUsages> usages) {
    this.usages = usages;
  }

  @Override
  public String toString() {
    return "Node{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", arch='" + arch + '\'' +
            ", os='" + os + '\'' +
            ", isMarster=" + isMarster +
            ", usages=" + usages +
            '}';
  }
}
