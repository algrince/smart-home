package com.algrince.smarthome.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DataTypeDTO {

    private Long id;
    private String name;
    private List<DeviceDTO> devices;

}
