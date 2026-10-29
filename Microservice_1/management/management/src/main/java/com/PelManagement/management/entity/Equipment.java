package com.PelManagement.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_code", nullable = false, unique = true)
    private String assetCode;

    @Column(nullable = false)
    private String name;

    private String category;

    private String location;

    @Column(name = "ownership_unit")
    private String ownershipUnit;

    private String status;

    @Column(name = "condition_status")
    private String conditionStatus;
}