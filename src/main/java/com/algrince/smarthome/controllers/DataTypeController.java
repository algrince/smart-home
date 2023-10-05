package com.algrince.smarthome.controllers;

import com.algrince.smarthome.dto.DataTypeDTO;
import com.algrince.smarthome.models.DataType;
import com.algrince.smarthome.services.DataTypeService;
import com.algrince.smarthome.utils.DTOMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("data-types")
@RequiredArgsConstructor
public class DataTypeController {

    private final DataTypeService dataTypeService;
    private final DTOMapper dtoMapper;

    @GetMapping("all")
    public List<DataTypeDTO> getDataTypesList() {

        List<DataType> dataTypes =  dataTypeService.findAll();

        return dtoMapper.mapList(dataTypes, DataTypeDTO.class);
    }

    @GetMapping("{id")
    public ResponseEntity<?> getDataType(@PathVariable("id") Long dataTypeId) {

        DataType foundDataType = dataTypeService.findById(dataTypeId);
        DataTypeDTO foundDataTypeDTO = dtoMapper.mapClass(foundDataType, DataTypeDTO.class);

        return ResponseEntity.ok().body(foundDataTypeDTO);
    }

    @PostMapping
    public ResponseEntity<?> addDataType(@RequestBody DataTypeDTO dataTypeDTO) {

        DataType dataType = dtoMapper.mapClass(dataTypeDTO, DataType.class);
        dataTypeService.addDataType(dataType);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDataType(
            @PathVariable("id") Long dataTypeId,
            @RequestBody DataTypeDTO dataTypeDTO) {

        DataType foundDataType = dataTypeService.findById(dataTypeId);

        dtoMapper.mapProperties(dataTypeDTO,foundDataType);
        dataTypeService.addDataType(foundDataType);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDataType(@PathVariable("id") Long dataTypeId) {

        dataTypeService.deleteDataType(dataTypeId);

        return ResponseEntity.ok().build();
    }
}
