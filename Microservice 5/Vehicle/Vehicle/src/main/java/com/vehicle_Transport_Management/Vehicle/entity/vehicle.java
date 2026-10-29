package com.vehicle_Transport_Management.Vehicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_no", length = 50, nullable = false, unique = true)
    private String registrationNo;

    @Column(name = "vehicle_type", length = 80, nullable = false)
    private String vehicleType;

    @Column(name = "driver_id")
    private Long driverId; // Agar aapko ManyToOne mapping karni hai toh Driver entity ke sath map kar sakte hain

    @Column(name = "location", length = 150)
    private String location;

    @Column(name = "status", length = 30)
    private String status; // Jaise AVAILABLE, IN_MISSION, MAINTENANCE

    @Column(name = "condition_status", length = 30)
    private String conditionStatus; // Jaise GOOD, DAMAGED, UNDER_REPAIR
}