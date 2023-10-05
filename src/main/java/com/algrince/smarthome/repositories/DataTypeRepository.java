package com.algrince.smarthome.repositories;

import com.algrince.smarthome.models.DataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTypeRepository extends JpaRepository<DataType, Long> {
}
