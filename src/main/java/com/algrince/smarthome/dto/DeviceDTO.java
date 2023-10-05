package com.algrince.smarthome.dto;

import com.algrince.smarthome.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceDTO {

    private Long id;
    private String name;
    private DeviceState deviceState;

}
