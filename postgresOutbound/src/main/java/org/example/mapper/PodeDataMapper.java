package org.example.mapper;

import org.example.entity.kube.Container;
import org.example.entity.kube.Pode;
import org.example.entity.kube.PodeUsages;
import org.example.module.kube.ContainerDto;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;
import java.time.LocalDateTime;

import java.util.ArrayList;

public class PodeDataMapper {
  public PodeDto podeToPodeDto(Pode pode) {
    PodeDto podeDto = new PodeDto();
    podeDto.setPodeUUId(pode.getPodeUUId());
    podeDto.setCreationTimestamp(pode.getCreationTimestamp());
    podeDto.setNameSpace(pode.getNameSpace());
    podeDto.setContainers(new ArrayList<>());
    for (Container container: pode.getContainers()) {
      podeDto.getContainers().add(this.containerTOContainerDto(container));
    }
    return podeDto;

  }

  private ContainerDto containerTOContainerDto(Container container) {
    ContainerDto containerDto = new ContainerDto();
    containerDto.setId(container.getId());
    containerDto.setName(container.getName());
    containerDto.setPodeUsageDto(new ArrayList<>());
    for (PodeUsages podeusages:container.getPodeUsages()) {
      containerDto.getPodeUsageDto().add(this.podeUsageToPodeUsagesDto(podeusages));

    }
    return containerDto;
  }

  private PodeUsageDto podeUsageToPodeUsagesDto(PodeUsages podeusages) {
    PodeUsageDto podeUsageDto = new PodeUsageDto();
    podeUsageDto.setMemory(podeusages.getMemory());
    podeUsageDto.setCpu(podeusages.getCpu());
    podeUsageDto.setId(podeUsageDto.getId());
    return podeUsageDto;
  }
}
