package com.algrince.smarthome.models;

import com.algrince.smarthome.enums.DeviceState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@Setter
@Getter
public class Device {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private DeviceState deviceState;

    @ManyToMany
    @JoinTable(
            name = "device_supported_types",
            joinColumns = @JoinColumn(name = "devices_id"),
            inverseJoinColumns = @JoinColumn(name = "registered_types_id")
    )
    private List<DataType> dataTypes;

}
