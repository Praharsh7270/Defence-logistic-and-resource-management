package com.vehicle_Transport_Management.Vehicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "license_number", length = 50, nullable = false, unique = true)
    private String licenseNumber;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "status", length = 30)
    private String status; // Jaise AVAILABLE, ON_DUTY, OFF_DUTY

    @Column(name = "assigned_vehicle_id")
    private Long assignedVehicleId;
}