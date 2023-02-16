package org.example.module.kube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class NodeDto {

  private Long id;
  private String name;
  private String arch;
  private String os;
  private boolean isMarster;

  public NodeDto() {
    this.id = 0L;
    this.name = "";
    this.arch = "";
    this.os = "";
    this.isMarster = false;
    this.usages = new ArrayList<>();
  }

  private List<NodeUsagesDto> usages;

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

  public List<NodeUsagesDto> getUsages() {
    return usages;
  }

  public void setUsages(List<NodeUsagesDto> usages) {
    this.usages = usages;
  }


}
