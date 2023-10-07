package com.algrince.smarthome.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "device_supported_types")
@NoArgsConstructor
@Setter
@Getter
public class SupportedByDeviceDataType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "devices_id", referencedColumnName = "id")
    private Device device;

    @OneToOne()
    @JoinColumn(name = "registered_types_id", referencedColumnName = "id")
    private DataType dataType;
}
