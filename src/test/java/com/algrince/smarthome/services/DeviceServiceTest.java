package com.algrince.smarthome.services;

import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.repositories.DeviceRepository;
import com.algrince.smarthome.utils.DataMapper;
import com.algrince.smarthome.validators.DeviceValidator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Test
    void create() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        DeviceValidator deviceValidator = spy(DeviceValidator.class);

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                mock(DataMapper.class),
                deviceValidator);

        Device device = new Device();
        device.setName("New device");
//        doThrow(new RuntimeException("Cannot save")).when(deviceRepository).save(eq(device));
        assertDoesNotThrow(() -> deviceService.create(device));
        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());
        verify(deviceValidator, times(1)).validate(device);

        assertEquals(device, deviceArgumentCaptor.getValue());
    }
}