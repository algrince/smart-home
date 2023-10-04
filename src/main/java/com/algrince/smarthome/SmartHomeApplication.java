package com.algrince.smarthome;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeApplication.class, args);
	}
}
