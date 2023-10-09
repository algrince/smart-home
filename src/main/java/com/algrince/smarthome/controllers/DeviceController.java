package com.algrince.smarthome.controllers;

import com.algrince.smarthome.dto.DeviceDTO;
import com.algrince.smarthome.models.Device;
import com.algrince.smarthome.services.DeviceService;
import com.algrince.smarthome.utils.DataMapper;
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
    private final DataMapper dataMapper;


    @GetMapping
    public List<DeviceDTO> getAll() {

        List<Device> devices = deviceService.findAll();

        return dataMapper.mapList(devices, DeviceDTO.class);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        Device foundDevice = deviceService.findById(id);
        DeviceDTO foundDeviceDTO = dataMapper.mapClass(foundDevice, DeviceDTO.class);

        return ResponseEntity.ok().body(foundDeviceDTO);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeviceDTO deviceDTO) {

        Device device = dataMapper.mapClass(deviceDTO, Device.class);
        deviceService.create(device);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{id}/switch")
    public ResponseEntity<?> switchState(@PathVariable("id") Long deviceId) {

        deviceService.switchState(deviceId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
        @PathVariable("id") Long deviceId,
        @RequestBody DeviceDTO deviceDTO) {

        Device updatedDevice = dataMapper.mapClass(deviceDTO, Device.class);
        deviceService.update(deviceId, updatedDevice);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long deviceId) {

        deviceService.delete(deviceId);

        return ResponseEntity.ok().build();
    }

}
