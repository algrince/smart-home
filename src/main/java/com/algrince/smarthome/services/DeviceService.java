package com.algrince.smarthome.services;

import com.algrince.smarthome.enums.DeviceState;
import com.algrince.smarthome.exceptions.ResourceNotFoundException;
import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.repositories.DeviceRepository;
import com.algrince.smarthome.utils.DataMapper;
import com.algrince.smarthome.validators.DeviceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DataMapper dataMapper;
    private final DeviceValidator deviceValidator;

    @Transactional(readOnly = true)
    public List<Device> findAll() {
        List<Device> devices = deviceRepository.findAll();
        return devices;
    }

    @Transactional(readOnly = true)
    public Device findById(Long deviceId) {
        Optional<Device> foundDevice = deviceRepository.findById(deviceId);
        return foundDevice.orElseThrow(()
            -> new ResourceNotFoundException("Device not found with id: " + deviceId));
    }

    @Transactional
    public void create(Device device) {
        deviceValidator.validate(device);
        deviceRepository.save(device);
    }

    @Transactional
    public void switchState(Long deviceId) {
        Device foundDevice = findById(deviceId);

        DeviceState deviceState = foundDevice.getDeviceState();

        switch (deviceState) {
            case ON -> foundDevice.setDeviceState(DeviceState.OFF);
            case OFF -> foundDevice.setDeviceState(DeviceState.ON);
            default ->  System.out.println("Cannot change the device status");

        }
        deviceRepository.save(foundDevice);
    }

    @Transactional
    public void update(Long deviceId, Device device) {
        deviceValidator.validate(device);

        Device foundDevice = findById(deviceId);
        deviceValidator.validateObjectById(foundDevice, device);

        dataMapper.mapProperties(device, foundDevice);
        deviceRepository.save(foundDevice);
    }

    @Transactional
    public void delete(Long deviceId) {

        deviceRepository.deleteById(deviceId);
    }
}
