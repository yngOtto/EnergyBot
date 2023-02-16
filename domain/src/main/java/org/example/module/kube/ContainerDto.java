package org.example.module.kube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContainerDto {
  private long id;
  private String name;
  private List<PodeUsageDto> podeUsageDto;

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

  public List<PodeUsageDto> getPodeUsageDto() {
    return podeUsageDto;
  }

  public void setPodeUsageDto(List<PodeUsageDto> podeUsageDto) {
    this.podeUsageDto = podeUsageDto;
  }
}
