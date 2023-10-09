package com.algrince.smarthome.controllers;

import com.algrince.smarthome.dto.DataTypeDTO;
import com.algrince.smarthome.models.DataType;
import com.algrince.smarthome.services.DataTypeService;
import com.algrince.smarthome.utils.DataMapper;
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
    private final DataMapper dataMapper;

    @GetMapping
    public List<DataTypeDTO> getAll() {

        List<DataType> dataTypes =  dataTypeService.findAll();

        return dataMapper.mapList(dataTypes, DataTypeDTO.class);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long dataTypeId) {

        DataType foundDataType = dataTypeService.findById(dataTypeId);
        DataTypeDTO foundDataTypeDTO = dataMapper.mapClass(foundDataType, DataTypeDTO.class);

        return ResponseEntity.ok().body(foundDataTypeDTO);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DataTypeDTO dataTypeDTO) {

        DataType dataType = dataMapper.mapClass(dataTypeDTO, DataType.class);
        dataTypeService.create(dataType);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long dataTypeId,
            @RequestBody DataTypeDTO dataTypeDTO) {

        DataType updatedDataType = dataMapper.mapClass(dataTypeDTO, DataType.class);
        dataTypeService.update(dataTypeId, updatedDataType);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long dataTypeId) {

        dataTypeService.delete(dataTypeId);

        return ResponseEntity.ok().build();
    }
}
