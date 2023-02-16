package org.example.module.utilmodels;

import java.util.ArrayList;
import java.util.List;

public class PowerDataObject {


  private List<Double> powerdata;
  private List<Double> movingAvagetPowerData;
  private static PowerDataObject INSTANCE=null;

  private PowerDataObject() {
    powerdata = new ArrayList<>();
    movingAvagetPowerData = new ArrayList<>();
  }

  public static PowerDataObject getInstance(){
    if(INSTANCE==null){
      INSTANCE= new PowerDataObject();
    }
    return INSTANCE;
  }


  public List<Double> getPowerdata() {
    return powerdata;
  }

  public void setPowerdata(List<Double> powerdata) {
    this.powerdata = powerdata;
  }

  public List<Double> getMovingAvagetPowerData() {
    return movingAvagetPowerData;
  }

  public void setMovingAvagetPowerData(List<Double> movingAvagetPowerData) {
    this.movingAvagetPowerData = movingAvagetPowerData;
  }

  @Override
  public String toString() {
    return "PowerDataObject{" +
            "powerdata=" + powerdata +
            ", movingAvagetPowerData=" + movingAvagetPowerData +
            '}';
  }
}
