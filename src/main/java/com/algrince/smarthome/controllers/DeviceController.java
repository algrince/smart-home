package com.algrince.smarthome.controllers;

import com.algrince.smarthome.dto.DeviceDTO;
import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.services.DeviceService;
import com.algrince.smarthome.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final DTOMapper dtoMapper;

    @GetMapping("all")
    public List<DeviceDTO> getDevicesList() {

        List<Device> devices = deviceService.findAll();

        return dtoMapper.mapList(devices, DeviceDTO.class);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDevice(@PathVariable("id") Long id) {

        Device foundDevice = deviceService.findById(id);
        DeviceDTO foundDeviceDTO = dtoMapper.mapClass(foundDevice, DeviceDTO.class);

        return ResponseEntity.ok().body(foundDeviceDTO);
    }

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody DeviceDTO deviceDTO) {

        Device device = dtoMapper.mapClass(deviceDTO, Device.class);
        deviceService.addDevice(device);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{id}/switch")
    public ResponseEntity<?> switchDevice(@PathVariable("id") Long deviceId) {

        deviceService.switchDevice(deviceId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDevice(
        @PathVariable("id") Long id,
        @RequestBody DeviceDTO deviceDTO) {
// move to service?
        Device foundDevice = deviceService.findById(id);

        dtoMapper.mapProperties(deviceDTO, foundDevice);
        deviceService.addDevice(foundDevice);

        return ResponseEntity.ok().build();
    }

//    add delete
}
