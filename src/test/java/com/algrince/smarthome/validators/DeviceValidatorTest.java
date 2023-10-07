package com.algrince.smarthome.validators;

import com.algrince.smarthome.models.Device;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceValidatorTest {

    @Test
    @DisplayName("Validate device before creation")
    void validate() {
        Device device = new Device();
        device.setName("New device");

        DeviceValidator deviceValidator = new DeviceValidator();

        assertDoesNotThrow(() -> deviceValidator.validate(device), "Device should be correct");
    }
}