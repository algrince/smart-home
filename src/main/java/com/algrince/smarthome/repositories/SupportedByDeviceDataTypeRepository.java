package com.algrince.smarthome.repositories;

import com.algrince.smarthome.models.SupportedByDeviceDataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportedByDeviceDataTypeRepository extends JpaRepository<SupportedByDeviceDataType, Long> {
}
