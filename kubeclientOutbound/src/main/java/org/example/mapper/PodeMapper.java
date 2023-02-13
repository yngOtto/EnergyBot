package org.example.mapper;

import org.example.entity.Pode;
import org.example.entity.PodeUsage;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;

public class PodeMapper {



  public static Pode podeDtoToPode(PodeDto podeDto){
    Pode pode = new Pode();
    pode.setPodeUUId(podeDto.getPodeUUId());
    pode.setNameSpace(podeDto.getNameSpace());
    pode.setName(podeDto.getName());
    pode.setCreationTimestamp(podeDto.getCreationTimestamp());
    return pode;
  }

  public static PodeDto podeToPodeDto(Pode pode){
    PodeDto podeDto = new PodeDto();
    podeDto.setPodeUUId(pode.getPodeUUId());
    podeDto.setNameSpace(pode.getNameSpace());
    podeDto.setName(pode.getName());
    podeDto.setCreationTimestamp(pode.getCreationTimestamp());
    return podeDto;
  }

  public static PodeUsageDto podeUsageToPodeUsageDto(PodeUsage podeUsage) {
    PodeUsageDto podeUsageDto = new PodeUsageDto();
    podeUsageDto.setCpu(podeUsage.getCpu());
    podeUsageDto.setMemory(podeUsage.getMemory());
    return podeUsageDto;
  }
}
