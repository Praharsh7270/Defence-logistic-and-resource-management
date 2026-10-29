package com.vehicle_Transport_Management.Vehicle.controller;

import com.vehicle_Transport_Management.Vehicle.entity.drivers;
import com.vehicle_Transport_Management.Vehicle.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<drivers> addDriver(@RequestBody drivers driver) {
        return ResponseEntity.ok(driverService.saveDriver(driver));
    }

    @GetMapping
    public ResponseEntity<List<drivers>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<drivers> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<drivers> updateDriver(@PathVariable Long id, @RequestBody drivers driverDetails) {
        return ResponseEntity.ok(driverService.updateDriver(id, driverDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok().build();
    }
}