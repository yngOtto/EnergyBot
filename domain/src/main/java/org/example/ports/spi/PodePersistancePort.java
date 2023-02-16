package org.example.ports.spi;

import org.example.module.kube.ContainerDto;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;

import java.util.List;

public interface PodePersistancePort {

  List<PodeDto> getPodes();
  PodeDto getPodeByName(String name);
  void update(PodeUsageDto podeUsageDto, String containername);
  void save(PodeDto podeDto);

  ContainerDto getContainerByNamwe(String Container);

  void saveContainer(ContainerDto containerDto);
}
