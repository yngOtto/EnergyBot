package org.example;

import org.example.ports.api.PowerServicePort;
import org.example.util.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PowerBot {

    private final String botName = "PowerBot";
    private Logger LOGGER = LoggerFactory.getLogger(PowerBot.class);
    @Autowired
    private PowerServicePort powerServicePort;

    private Lock lock = Lock.getInstance();

    @Scheduled(fixedRate = 1000)
    public void job(){
        if(!Lock.getInstance().isLocked()){
            Lock.getInstance().setLocked(true);
            if (powerServicePort.startJob()) {
                Lock.getInstance().setLocked(false);
            }
        }

    }
}
