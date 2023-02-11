package org.example;

import java.util.Random;
import java.util.Date;

import org.example.entity.PowerData;

public final class RequestHandler {
    private static RequestHandler instance = null;

    private static final int trendLevel = 10;
    private static final int minValue = 0;
    private static final int maxValue = 100;

    private int previousValue;
    private Date timestamp;

    private RequestHandler() {
        Random random = new Random();
        previousValue = random.nextInt(maxValue - minValue + 1) + minValue;
        timestamp = new Date();
    }

    public static RequestHandler getInstance() {
        if (instance == null) {
            instance = new RequestHandler();
        }
        return instance;
    }

    public PowerData receivePowerData() {
        Random random = new Random();
        int trendDirection = random.nextInt(2);
        int currentValue;
        if (trendDirection == 0) {
            currentValue = previousValue - random.nextInt(trendLevel + 1);
        } else {
            currentValue = previousValue + random.nextInt(trendLevel + 1);
        }
        if (currentValue < minValue) {
            currentValue = minValue;
        }
        if (currentValue > maxValue) {
            currentValue = maxValue;
        }
        timestamp = new Date();
        previousValue = currentValue;

        PowerData powerData = new PowerData();
        powerData.setCurrentValue(currentValue);
        powerData.setCreationTimestamp(timestamp.toString());
        return powerData;
    }
}