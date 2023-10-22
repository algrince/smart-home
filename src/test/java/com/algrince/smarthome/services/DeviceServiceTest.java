package com.algrince.smarthome.services;

import com.algrince.smarthome.configs.TestDBConfig;
import com.algrince.smarthome.enums.DeviceState;
import com.algrince.smarthome.exceptions.InvalidFormException;
import com.algrince.smarthome.exceptions.ResourceNotFoundException;
import com.algrince.smarthome.models.DataType;
import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.repositories.DeviceRepository;
import com.algrince.smarthome.utils.DataMapper;
import com.algrince.smarthome.validators.DeviceValidator;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestDBConfig.class)

class DeviceServiceTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    @Transactional
    @DisplayName("Test findAll() method")
    void findAll() {

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                mock(DataMapper.class),
                mock(DeviceValidator.class));

        assertDoesNotThrow(deviceService::findAll);

        List<Device> foundDevicesByService = deviceService.findAll();
        List<Device> foundDevicesByRepository = deviceRepository.findAll();
        assertEquals(foundDevicesByService.size(), foundDevicesByRepository.size());
    }

    @Test
    @Transactional
    @DisplayName("Test findById() method")
    void findById() {

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                mock(DataMapper.class),
                mock(DeviceValidator.class));

        assertDoesNotThrow(() -> deviceService.findById(1L));
        assertThrowsExactly(ResourceNotFoundException.class,
                () -> deviceService.findById(111L));
    }

    @Test
    @Transactional
    @DisplayName("Test create() method")
    void create() {
//        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        DeviceValidator deviceValidator = spy(DeviceValidator.class);

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                mock(DataMapper.class),
                deviceValidator);

        List<DataType> dataTypes = new ArrayList<DataType>();

        Device device = new Device(100L, "Phone", DeviceState.ON, dataTypes);
//        doThrow(new RuntimeException("Cannot save")).when(deviceRepository).save(eq(device));
        assertDoesNotThrow(() -> deviceService.create(device));
//        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
//        verify(deviceRepository).save(deviceArgumentCaptor.capture());
        verify(deviceValidator, times(1)).validate(device);
//        assertEquals(device, deviceArgumentCaptor.getValue());

        Device savedDevice = deviceRepository.findById(device.getId()).orElse(null);
        assertTrue(new ReflectionEquals(savedDevice).matches(device));

    }

    @Test
    @Transactional
    @DisplayName("Test update() method")
    void update() {

        DeviceValidator deviceValidator = spy(DeviceValidator.class);

        DataMapper dataMapper = new DataMapper(spy(ModelMapper.class));

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                dataMapper,
                deviceValidator);

        List<DataType> dataTypes = new ArrayList<DataType>();

        Device deviceToUpdate = new Device(1L, "Phone", DeviceState.ON, dataTypes);
        Long deviceIdToUpdate = deviceToUpdate.getId();

        assertDoesNotThrow(() -> deviceService.update(deviceIdToUpdate, deviceToUpdate));

        Device savedDevice = deviceRepository.findById(deviceIdToUpdate).orElse(null);

        assertTrue(new ReflectionEquals(savedDevice).matches(deviceToUpdate));

        Long wrongId = 3L;
        assertThrowsExactly(InvalidFormException.class,
                () -> deviceService.update(wrongId, deviceToUpdate));
    }


    @Test
    @Transactional
    @DisplayName("Test delete() method")
    void delete() {

        DeviceService deviceService = new DeviceService(
                deviceRepository,
                mock(DataMapper.class),
                mock(DeviceValidator.class));

        List<Device> devicesBefore = deviceRepository.findAll();
        assertDoesNotThrow(() -> deviceService.delete(1L));
        List<Device> devicesAfter = deviceRepository.findAll();

        assertEquals(devicesBefore.size() - 1, devicesAfter.size());

        assertThrowsExactly(ResourceNotFoundException.class,
                () -> deviceService.delete(100L));

    }

}