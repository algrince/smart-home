package com.algrince.smarthome.inputs;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureListener {

    @KafkaListener(topics = "temperature", groupId = "try", containerFactory = "tryKafkaListenerContainerFactory")
    public void listenTemperature(String message) {
        System.out.println("Received message: " + message);
    }
}
