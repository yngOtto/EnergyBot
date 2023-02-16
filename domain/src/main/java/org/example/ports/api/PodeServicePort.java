package org.example.ports.api;

import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;

import java.util.List;

public interface PodeServicePort {
  List<PodeDto> getPodes();
  PodeDto getPodeByName(String name);
  PodeDto updatePode(PodeDto podeDto);
  PodeDto savePode(PodeDto podeDto);
  void updatePodeUsageByContainerName(String contatinerName);
}
