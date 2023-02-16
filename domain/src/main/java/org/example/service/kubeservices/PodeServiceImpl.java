package org.example.service.kubeservices;

import org.example.module.kube.ContainerDto;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;
import org.example.ports.api.PodeServicePort;
import org.example.ports.spi.PodePersistancePort;
import org.example.ports.spi.KubePodePersistancePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PodeServiceImpl implements PodeServicePort {

  private Logger LOGGER = LoggerFactory.getLogger(PodeServiceImpl.class);

  private PodePersistancePort podePersistancePort;
  private KubePodePersistancePort kubePodePersistancePort;

  public PodeServiceImpl(PodePersistancePort podePersistancePort, KubePodePersistancePort kubePodePersistancePort) {
    this.podePersistancePort = podePersistancePort;
    this.kubePodePersistancePort = kubePodePersistancePort;
  }

  @Override
  public List<PodeDto> getPodes() {
    return this.podePersistancePort.getPodes();
  }

  @Override
  public PodeDto getPodeByName(String name) {
    return this.podePersistancePort.getPodeByName(name);
  }

  @Override
  public PodeDto updatePode(PodeDto podeDto) {
    return null;
  }

  @Override
  public PodeDto savePode(PodeDto podeDto) {
    this.podePersistancePort.save(podeDto);
    return null;
  }

  @Override
  public void updatePodeUsageByContainerName(String contatinerName) {
    LOGGER.info("[Updating usages] containername : "+contatinerName);
    ContainerDto containerDto = this.podePersistancePort.getContainerByNamwe(contatinerName);
    PodeUsageDto podeUsageDto = this.kubePodePersistancePort.getPodeUsageByContainerName(contatinerName);
    LOGGER.info("[Saving new usages]");
    containerDto.getPodeUsageDto().add(podeUsageDto);
    this.podePersistancePort.saveContainer(containerDto);
  }
}
