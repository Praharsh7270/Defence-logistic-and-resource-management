package com.vehicle_Transport_Management.Vehicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId; // Yeh vehicles table ki id se linked hoga[cite: 3, 4]

    @Column(name = "driver_id", nullable = false)
    private Long driverId; // Yeh drivers table ki id se linked hoga[cite: 5]

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @Column(name = "status", length = 30, nullable = false)
    private String status = "ACTIVE"; // Jaise ACTIVE, COMPLETED, CANCELLED
}