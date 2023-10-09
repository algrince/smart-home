package com.algrince.smarthome.services;

import com.algrince.smarthome.exceptions.ResourceNotFoundException;
import com.algrince.smarthome.models.DataType;
import com.algrince.smarthome.repositories.DataTypeRepository;
import com.algrince.smarthome.utils.DataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataTypeService {

    private final DataTypeRepository dataTypeRepository;
    private final DataMapper dataMapper;

    @Transactional(readOnly = true)
    public List<DataType> findAll() {
        List<DataType> dataTypes = dataTypeRepository.findAll();
        return dataTypes;
    }

    @Transactional(readOnly = true)
    public DataType findById(Long dataTypeIdd) {
        Optional<DataType> foundDataType = dataTypeRepository.findById(dataTypeIdd);
        return foundDataType.orElseThrow(()
            -> new ResourceNotFoundException("Data type not found with id: " + dataTypeIdd));
    }

    @Transactional
    public void create(DataType dataType) {
        dataTypeRepository.save(dataType);
    }

    @Transactional
    public void update(Long dataTypeId, DataType dataType) {
        DataType foundDataType = findById(dataTypeId);
        dataMapper.mapProperties(dataType, foundDataType);
        dataTypeRepository.save(foundDataType);
    }

    @Transactional
    public void delete(Long dataTypeId) {

        dataTypeRepository.deleteById(dataTypeId);
    }

}
