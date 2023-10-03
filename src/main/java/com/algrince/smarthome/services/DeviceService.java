package com.algrince.smarthome.services;

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
    public Device findById(Long id) {
        Optional<Device> foundDevice = deviceRepository.findById(id);
        return foundDevice.orElseThrow(()
            -> new ResourceNotFoundException("Device not found with id: " + id));
    }

    @Transactional
    public void save(Device device) {
        deviceRepository.save(device);
    }
}
