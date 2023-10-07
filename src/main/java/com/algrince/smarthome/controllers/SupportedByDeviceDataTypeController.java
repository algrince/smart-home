package com.algrince.smarthome.controllers;

import com.algrince.smarthome.dto.SupportedByDeviceDataTypeDTO;
import com.algrince.smarthome.models.SupportedByDeviceDataType;
import com.algrince.smarthome.services.SupportedByDeviceDataTypeService;
import com.algrince.smarthome.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supported-data-types")
@RequiredArgsConstructor
public class SupportedByDeviceDataTypeController {

    private final SupportedByDeviceDataTypeService supportedByDeviceDataTypeService;
    private final DTOMapper dtoMapper;

    @GetMapping("all")
    public List<SupportedByDeviceDataTypeDTO> getSupportedByDeviceDataTypes() {

        List<SupportedByDeviceDataType> supportedByDeviceDataTypes = supportedByDeviceDataTypeService.findAll();

        return dtoMapper.mapList(supportedByDeviceDataTypes, SupportedByDeviceDataTypeDTO.class);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSupportedByDeviceDataType(@PathVariable("id") Long id) {

        SupportedByDeviceDataType foundSupportedByDeviceDataType = supportedByDeviceDataTypeService.findById(id);
        SupportedByDeviceDataTypeDTO foundSupportedByDeviceDataTypeDTO = dtoMapper.mapClass(
                foundSupportedByDeviceDataType, SupportedByDeviceDataTypeDTO.class);

        return ResponseEntity.ok().body(foundSupportedByDeviceDataTypeDTO);
    }

    @PostMapping
    public ResponseEntity<?> addSupportedByDeviceDataType(
            @RequestBody SupportedByDeviceDataTypeDTO supportedByDeviceDataTypeDTO
    ) {

        SupportedByDeviceDataType supportedByDeviceDataType = dtoMapper.mapClass(
                supportedByDeviceDataTypeDTO, SupportedByDeviceDataType.class
        );
        supportedByDeviceDataTypeService.addSupportedByDeviceDataType(supportedByDeviceDataType);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateSupportedByDeviceDataType(
            @PathVariable("id") Long id,
            @RequestBody SupportedByDeviceDataTypeDTO supportedByDeviceDataTypeDTO
    ) {

        SupportedByDeviceDataType foundSupportedByDeviceDataType = supportedByDeviceDataTypeService.findById(id);

        dtoMapper.mapProperties(supportedByDeviceDataTypeDTO, foundSupportedByDeviceDataType);
        supportedByDeviceDataTypeService.addSupportedByDeviceDataType(foundSupportedByDeviceDataType);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSupportedByDeviceDataType(
            @PathVariable("id") Long id
    ) {

        supportedByDeviceDataTypeService.deleteSupportedByDeviceDataType(id);

        return ResponseEntity.ok().build();
    }
}
