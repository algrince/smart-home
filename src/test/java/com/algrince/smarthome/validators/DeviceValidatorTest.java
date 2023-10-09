package com.algrince.smarthome.validators;

import com.algrince.smarthome.enums.DeviceState;
import com.algrince.smarthome.exceptions.InvalidFormException;
import com.algrince.smarthome.models.DataType;
import com.algrince.smarthome.models.Device;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeviceValidatorTest {

    @Test
    @DisplayName("Successful validation for a device")
    void validatePositive() {

        List<DataType> dataTypes = new ArrayList<DataType>();
        Device device = new Device(1L, "Phone", DeviceState.ON, dataTypes);

        DeviceValidator deviceValidator = new DeviceValidator();

        assertDoesNotThrow(() -> deviceValidator.validate(device), "Device should be correct");
    }

    @Test
    @DisplayName("Empty name exception")
    void validateNotEmptyName() {

        List<DataType> dataTypes = new ArrayList<DataType>();
        Device device = new Device(1L, "", DeviceState.ON, dataTypes);

        DeviceValidator deviceValidator = new DeviceValidator();

        assertThrowsExactly(InvalidFormException.class,
                () -> deviceValidator.validate(device));
    }

    @Test
    @DisplayName("Zero id exception")
    void validateNotZeroId() {

        List<DataType> dataTypes = new ArrayList<DataType>();
        Device device = new Device(0L, "Phone", DeviceState.ON, dataTypes);

        DeviceValidator deviceValidator = new DeviceValidator();

        assertThrowsExactly(InvalidFormException.class,
                () -> deviceValidator.validate(device));
    }

    @Test
    @DisplayName("Null exception")
    void validateNotNull() {

        List<DataType> dataTypes = new ArrayList<DataType>();
//        Device nullIdDevice = new Device(null, "Phone", DeviceState.ON, dataTypes);
        Device nullNameDevice = new Device(1L, null, DeviceState.ON, dataTypes);
        Device nullStateDevice = new Device(1L, "Phone", null, dataTypes);

        DeviceValidator deviceValidator = new DeviceValidator();

//        assertThrowsExactly(InvalidFormException.class,
//                () -> deviceValidator.validate(nullIdDevice));
        assertThrowsExactly(InvalidFormException.class,
                () -> deviceValidator.validate(nullNameDevice));
        assertThrowsExactly(InvalidFormException.class,
                () -> deviceValidator.validate(nullStateDevice));
    }


    @Test
    @DisplayName("Two devices' id validation")
    void validateObjectById() {

        List<DataType> dataTypes = new ArrayList<DataType>();

        Device foundDevice = new Device(1L, "Phone", DeviceState.ON, dataTypes);
        Device wrongIdDevice = new Device(23L, "Air conditioner", DeviceState.ON, dataTypes);
        Device rightIdDevice = new Device(1L, "Air conditioner", DeviceState.ON, dataTypes);

        DeviceValidator deviceValidator = new DeviceValidator();

        assertThrowsExactly(InvalidFormException.class,
                () -> deviceValidator.validateObjectById(foundDevice, wrongIdDevice));
        assertDoesNotThrow(() -> deviceValidator.validateObjectById(foundDevice, rightIdDevice));
    }
}