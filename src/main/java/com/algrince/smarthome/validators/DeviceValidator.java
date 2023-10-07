package com.algrince.smarthome.validators;

import com.algrince.smarthome.models.Device;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DeviceValidator {
    public void validate(Device device) {
        if (!StringUtils.hasText(device.getName())) {
            throw new IllegalArgumentException("Name should not be empty");
        }
    }
}
