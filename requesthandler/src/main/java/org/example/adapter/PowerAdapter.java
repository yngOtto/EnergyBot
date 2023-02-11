package org.example.adapter;

import org.example.RequestHandler;
import org.example.entity.PowerData;
import org.example.module.kube.PowerDto;
import org.example.ports.spi.PowerPersistancePort;
import org.springframework.stereotype.Service;

@Service
public class PowerAdapter implements PowerPersistancePort {


  @Override
  public PowerDto getPowerData() {
    PowerData pwrData = RequestHandler.getInstance().receivePowerData();
    PowerDto powerDto = new PowerDto();
    powerDto.setCreationTimestamp(pwrData.getCreationTimestamp());
    powerDto.setCurrentValue(pwrData.getCurrentValue());
    return powerDto;
  }
}
