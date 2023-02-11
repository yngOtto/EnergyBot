package org.example.entity.kube;

import lombok.*;
import org.example.module.kube.ContainerDto;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pode {
  @Id
  @Column (name = "id", nullable = false)
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false,unique = true)
  private String name;

  private UUID podeUUId;
  private String nameSpace;

  private String creationTimestamp;

  @OneToMany(mappedBy = "pode", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL)
  @Column(nullable = true)
  private List<Container> containers;

  public UUID getPodeUUId() {
    return podeUUId;
  }

  public void setPodeUUId(UUID podeUUId) {
    this.podeUUId = podeUUId;
  }

  public String getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(String creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
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

  public String getNameSpace() {
    return nameSpace;
  }

  public void setNameSpace(String nameSpace) {
    this.nameSpace = nameSpace;
  }

  public List<Container> getContainers() {
    return containers;
  }

  public void setContainers(List<Container> containers) {
    this.containers = containers;
  }
}
