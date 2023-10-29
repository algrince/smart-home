package com.algrince.smarthome.dto;

import com.algrince.smarthome.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class DeviceDTO {

    private Long id;
    private String name;
    private DeviceState deviceState;
    private List<DataTypeDTO> dataTypes;

}
