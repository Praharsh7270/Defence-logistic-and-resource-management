package com.vehicle_Transport_Management.Vehicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "movement_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_by", length = 100, nullable = false)
    private String requestedBy;

    @Column(name = "purpose", length = 255, nullable = false)
    private String purpose;

    @Column(name = "source", length = 150, nullable = false)
    private String source;

    @Column(name = "destination", length = 150, nullable = false)
    private String destination;

    @Column(name = "required_vehicle_type", length = 80)
    private String requiredVehicleType;

    @Column(name = "status", length = 30, nullable = false)
    private String status = "PENDING"; // Jaise PENDING, APPROVED, REJECTED, COMPLETED

    @Column(name = "request_date")
    private LocalDateTime requestDate;
}