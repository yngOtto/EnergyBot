package org.example.ports.spi;

import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;

import java.io.IOException;
import java.util.List;

public interface KubePodePersistancePort {

  List<PodeDto> getPodes() throws IOException;
  PodeDto getPodeByName(String name);
  PodeDto updatePode(PodeDto podeDto);
  PodeDto savePode(PodeDto podeDto);
  PodeUsageDto getPodeUsageByContainerName(String ContatinerName);
}
