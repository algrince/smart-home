package com.algrince.smarthome.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceDTO {

    private Long id;

    private String name;

    private boolean isOn;
}
