package com.algrince.smarthome.services;

import com.algrince.smarthome.enums.DeviceState;
import com.algrince.smarthome.exceptions.ResourceNotFoundException;
import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

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
    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    @Transactional
    public void switchDevice(Long deviceId) {
        Device foundDevice = findById(deviceId);

        DeviceState deviceState = foundDevice.getDeviceState();

        if (deviceState == DeviceState.ON) {
            foundDevice.setDeviceState(DeviceState.OFF);
        } else if (deviceState == DeviceState.OFF){
            foundDevice.setDeviceState(DeviceState.ON);
        } else {
//            exception?
            System.out.println("Cannot change the device status");
        }

        addDevice(foundDevice);
    }

    @Transactional
    public void deleteDevice(Long deviceId) {

        deviceRepository.deleteById(deviceId);
    }
}
