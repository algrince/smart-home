package com.algrince.smarthome.inputs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class AirTemperature {

    public int periodOfGenerationInMin = 1;
    public int periodOfGenerationInSec = periodOfGenerationInMin * 60;

    public int minTemp = 16;
    public int maxTemp = 32;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostConstruct
    public void sendAirTemperature() {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int airTemperature = random.nextInt(maxTemp - minTemp + 1) + minTemp;
                System.out.println(
                        String.format("The temperature sender is not yet implemented. The air temperature is: %d", airTemperature)
                );

                kafkaTemplate.send("temperature", String.valueOf(airTemperature));
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, periodOfGenerationInSec * 1000);
    }

}
