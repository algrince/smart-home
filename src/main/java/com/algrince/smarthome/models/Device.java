package com.algrince.smarthome.models;

import com.algrince.smarthome.enums.DeviceState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
