package org.example.adapters;

import io.kubernetes.client.openapi.ApiException;
import org.example.entity.Pode;
import org.example.entity.PodeUsage;
import org.example.kubernetesClient.PodeHandler;
import org.example.mapper.PodeMapper;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;
import org.example.ports.spi.KubePodePersistancePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class KubePodeAdapter implements KubePodePersistancePort {

  private Logger LOGGER = LoggerFactory.getLogger(KubePodeAdapter.class);

  private PodeHandler podeHandler = new PodeHandler();



  @Override
  public List<PodeDto> getPodes() throws IOException {
    try {
      List<PodeDto> podeDtoList = new ArrayList<>();
      for (Pode pode:podeHandler.getpods("default")) {
        podeDtoList.add(PodeMapper.podeToPodeDto(pode));
      }
      return podeDtoList;
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public PodeDto getPodeByName(String name) {
    try {
      for (PodeDto podeDto:getPodes()) {
        if(podeDto.getName().equals(name)){
          return podeDto;
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public PodeDto updatePode(PodeDto podeDto) {
    return null;
  }

  @Override
  public PodeDto savePode(PodeDto podeDto) {
    return null;
  }

  @Override
  public PodeUsageDto getPodeUsageByContainerName(String contatinerName) {
    try {
      PodeUsage podeUsage = podeHandler.ContainerUpdate(contatinerName);
      PodeUsageDto podeUsageDto = PodeMapper.podeUsageToPodeUsageDto(podeUsage);
    } catch (IOException | ApiException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
