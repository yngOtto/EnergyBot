package org.example.entity.kube;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {
  @Id
  @Column (name = "id", nullable = false)
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false,unique = true)
  private String name;
  private String arch;
  private String os;
  private boolean isMarster;
  @OneToMany(mappedBy = "node", fetch = FetchType.EAGER,
          cascade = CascadeType.ALL)
  @Column(nullable = true)
  private List<NodeUsages> usages;


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
