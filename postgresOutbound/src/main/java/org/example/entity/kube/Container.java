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
public class Container {
  @Id
  @Column (name = "id", nullable = false)
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "pode_id", nullable = false)
  private Pode pode;


  @OneToMany(mappedBy = "container", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL)
  @Column(nullable = true)
  private List<PodeUsages> podeUsages;

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

  public Pode getPode() {
    return pode;
  }

  public void setPode(Pode pode) {
    this.pode = pode;
  }

  public List<PodeUsages> getPodeUsages() {
    return podeUsages;
  }

  public void setPodeUsages(List<PodeUsages> podeUsages) {
    this.podeUsages = podeUsages;
  }
}
