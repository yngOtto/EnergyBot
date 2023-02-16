package org.example.service.kubeservices;

import org.example.module.kube.DeploymentDto;
import org.example.module.kube.PowerDto;
import org.example.module.usermodels.MemberDto;
import org.example.module.utilmodels.PowerDataObject;
import org.example.ports.api.PowerServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.PowerPersistancePort;
import org.example.ports.spi.KubeDeploymentsPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PowerServiceImpl implements PowerServicePort {

    private Logger LOGGER = LoggerFactory.getLogger(PowerServiceImpl.class);

    private final int MOVING_AVAGET_LENGHT=25;
    private PowerDataObject powerDataObject;
    private PowerPersistancePort powerPersistancePort;

    private MemberPersistencePort memberPersistencePort;

    private MailPersistencePort mailPersistencePort;


    private KubeDeploymentsPersistencePort kubeDeploymentsPersistencePort;

    public PowerServiceImpl(PowerPersistancePort powerPersistancePort, MemberPersistencePort memberPersistencePort, MailPersistencePort mailPersistencePort, KubeDeploymentsPersistencePort kubeDeploymentsPersistencePort) {
        this.powerPersistancePort = powerPersistancePort;
        this.memberPersistencePort = memberPersistencePort;
        this.mailPersistencePort = mailPersistencePort;
        this.kubeDeploymentsPersistencePort = kubeDeploymentsPersistencePort;
    }

    @Override
    public boolean startJob() {

        PowerDto powerDto = this.powerPersistancePort.getPowerData();
        if(this.readyForBot(powerDto)){
            try {
                List< DeploymentDto> deploymentDtoList= this.kubeDeploymentsPersistencePort.getDeployments();
                for (DeploymentDto deployment: deploymentDtoList) {
                    if(!deployment.getName().contains("post") ){
                        if(!deployment.getName().contains("pg")){
                            this.action(deployment);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private void action(DeploymentDto deployment){
        powerDataObject = PowerDataObject.getInstance();
        double lastMovingAvaget = powerDataObject.getMovingAvagetPowerData().get(powerDataObject.getMovingAvagetPowerData().size()-1);
        double secuntLastMovingAvaget = powerDataObject.getMovingAvagetPowerData().get(powerDataObject.getMovingAvagetPowerData().size()-2);
        double currentdata = powerDataObject.getPowerdata().get(powerDataObject.getPowerdata().size()-1);
        if(lastMovingAvaget>currentdata && currentdata>secuntLastMovingAvaget){
            // price going down this up scaling
            if(deployment.getReplicas()<10) {
                this.kubeDeploymentsPersistencePort.createScallingRequst(deployment.getName(), deployment.getNameSpace(), deployment.getReplicas() + 1);
                sendMail(deployment.getName(), deployment.getReplicas(), deployment.getReplicas()+1);
            }
        }
        if(lastMovingAvaget<currentdata && currentdata<secuntLastMovingAvaget){
            // price going UP then down scaling
            if(deployment.getReplicas()>1){
                this.kubeDeploymentsPersistencePort.createScallingRequst(deployment.getName(), deployment.getNameSpace(), deployment.getReplicas()-1);
                sendMail(deployment.getName(), deployment.getReplicas(), deployment.getReplicas()-1);
            }
        }
    }

    private void sendMail(String name, int previusreplica, int newreplica){
        List<MemberDto> memberDtoList = this.memberPersistencePort.getAllMembers();
        String sub ="Scalling Deployment : "+name;
        String message= "A new Scalling job is added\nScalling "+name+"\nDate: "+new Date().toString()+"\nRelicas set to "+newreplica+" From "+previusreplica;
        for (MemberDto memberdto:memberDtoList) {
            this.mailPersistencePort.sendEmail(memberdto.getEmail(),sub,message);
        }
    }
    private boolean readyForBot(PowerDto powerDto){
        powerDataObject = PowerDataObject.getInstance();
        if(powerDataObject.getPowerdata().size()>MOVING_AVAGET_LENGHT*2){
            powerDataObject.getPowerdata().remove(0);
        }
        if(powerDataObject.getMovingAvagetPowerData().size()>MOVING_AVAGET_LENGHT*2){
            powerDataObject.getMovingAvagetPowerData().remove(0);
        }
        powerDataObject.getPowerdata().add(powerDto.getCurrentValue());
        if(validateDataLength(MOVING_AVAGET_LENGHT)){
            powerDataObject.getMovingAvagetPowerData().add(caluclaAvaget(9,powerDataObject.getPowerdata()));
            if(powerDataObject.getMovingAvagetPowerData().size()>MOVING_AVAGET_LENGHT){
                return true;
            }
        }
        return false;
    }
    private boolean validateDataLength(int size){
        if(powerDataObject.getPowerdata().size()>size){
            return true;
        }
        return false;
    }

    private Double caluclaAvaget(int size, List<Double> data){
        List<Double> newDataList = new ArrayList<>();
        for(int i= size; i>0;i--){
            newDataList.add(data.get(data.size()-i));
        }
        double avaget = 0;
        for (Double value: newDataList) {
            avaget+= value;
        }
        return avaget/size;
    }


}
