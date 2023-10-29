package com.algrince.smarthome.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class DataTypeDTO {

    private Long id;
    private String name;
    private List<DeviceDTO> devices;

}
