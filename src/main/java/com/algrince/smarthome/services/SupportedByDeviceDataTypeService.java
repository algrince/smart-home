package com.algrince.smarthome.services;

import com.algrince.smarthome.exceptions.ResourceNotFoundException;
import com.algrince.smarthome.models.SupportedByDeviceDataType;
import com.algrince.smarthome.repositories.SupportedByDeviceDataTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportedByDeviceDataTypeService {

    private final SupportedByDeviceDataTypeRepository supportedByDeviceDataTypeRepository;

    @Transactional(readOnly = true)
    public List<SupportedByDeviceDataType> findAll() {
        List<SupportedByDeviceDataType> supportedByDeviceDataTypes = supportedByDeviceDataTypeRepository.findAll();
        return supportedByDeviceDataTypes;
    }

    @Transactional(readOnly = true)
    public SupportedByDeviceDataType findById(Long id) {
        Optional<SupportedByDeviceDataType> foundSupportedByDeviceDataType = supportedByDeviceDataTypeRepository.findById(id);
        return foundSupportedByDeviceDataType.orElseThrow(()
            -> new ResourceNotFoundException("Data type supported by device not found with id: " + id));
    }

    @Transactional
    public void addSupportedByDeviceDataType(SupportedByDeviceDataType supportedByDeviceDataType) {
        supportedByDeviceDataTypeRepository.save(supportedByDeviceDataType);
    }

    @Transactional
    public void deleteSupportedByDeviceDataType(Long id) {
        supportedByDeviceDataTypeRepository.deleteById(id);
    }
}
