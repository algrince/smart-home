package com.algrince.smarthome.validators;

import com.algrince.smarthome.exceptions.InvalidFormException;
import com.algrince.smarthome.models.Device;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DeviceValidator {
    public void validate(Device device) {
        if (!StringUtils.hasText(device.getName())) {
            throw new InvalidFormException("Name should not be empty");
        }

//        if (device.getId() == 0) {
//            throw new InvalidFormException("Device id cannot be zero");
//        }

        if (device.getName() == null || device.getDeviceState() == null) {
            throw new InvalidFormException("All data should be filled");
        }
    }

    public void validateObjectById(Device foundDevice, Device updatedDevice) {
        if (updatedDevice.getId() != foundDevice.getId()) {
            throw new InvalidFormException("It is impossible to change the id of registered device");
        }
    }
}
