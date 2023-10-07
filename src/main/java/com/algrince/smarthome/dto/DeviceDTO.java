package com.algrince.smarthome.dto;

import com.algrince.smarthome.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeviceDTO {

    private Long id;
    private String name;
    private DeviceState deviceState;
    private List<DataTypeDTO> dataTypes;

}
