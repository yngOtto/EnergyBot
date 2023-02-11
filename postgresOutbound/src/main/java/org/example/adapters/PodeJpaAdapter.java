package org.example.adapters;

import org.example.entity.kube.Pode;
import org.example.mapper.PodeDataMapper;
import org.example.module.kube.ContainerDto;
import org.example.module.kube.PodeDto;
import org.example.module.kube.PodeUsageDto;
import org.example.ports.spi.PodePersistancePort;
import org.example.repositorys.PodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PodeJpaAdapter implements PodePersistancePort {

  private Logger LOGGER = LoggerFactory.getLogger(PodeJpaAdapter.class);

  @Autowired
  private PodeRepository podeRepository;

  private PodeDataMapper podeDataMapper;


  @Override
  public List<PodeDto> getPodes() {
    List<PodeDto> podeDtoList = new ArrayList<>();
    List<Pode> podelist = podeRepository.findAll();
    for (Pode pode:podelist) {
      podeDtoList.add(podeDataMapper.podeToPodeDto(pode));
    }
    return podeDtoList;
  }

  @Override
  public PodeDto getPodeByName(String name) {
    Optional<Pode> pode = this.podeRepository.findAllByName(name);
    if(pode.isPresent()){
      return podeDataMapper.podeToPodeDto(pode.get());
    }
    return null;
  }

  @Override
  public void update(PodeUsageDto podeUsageDto, String containername) {

  }

  @Override
  public void save(PodeDto podeDto) {


  }

  @Override
  public ContainerDto getContainerByNamwe(String Container) {
    return null;
  }

  @Override
  public void saveContainer(ContainerDto containerDto) {

  }
}
