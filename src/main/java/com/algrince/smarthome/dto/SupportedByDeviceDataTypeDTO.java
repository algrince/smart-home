package com.algrince.smarthome.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupportedByDeviceDataTypeDTO {

    private Long id;
    private DeviceDTO device;
    private DataTypeDTO dataType;
}
