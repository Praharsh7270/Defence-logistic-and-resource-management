package com.defense.maintainace.controller;
import java.util.List;
import java.util.Map;
import com.defense.maintainace.dto.CreateMaintenanceRequest;
import com.defense.maintainace.dto.ReviewMaintenanceRequest;
import com.defense.maintainace.dto.UpdateMaintenanceStatusRequest;
import com.defense.maintainace.service.MaintenanceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/maintenance")
@Validated
public class MaintenanceController {
 private final MaintenanceService service;
 public MaintenanceController(MaintenanceService service){this.service=service;}
 @PostMapping("/requests")
 public Map<String,Object> create(@Valid @RequestBody CreateMaintenanceRequest r){return service.create(r);}
 @GetMapping("/my-requests")
 public List<Map<String,Object>> mine(@RequestParam @Positive long requesterId){return service.myRequests(requesterId);}
 @GetMapping("/requests")
 public List<Map<String,Object>> all(){return service.allRequests();}
 @GetMapping("/requests/{id}")
 public Map<String,Object> get(@PathVariable @Positive long id){return service.get(id);}
 @GetMapping("/requests/{id}/history")
 public List<Map<String,Object>> history(@PathVariable @Positive long id){return service.history(id);}
 @PatchMapping("/requests/{id}/review")
 public Map<String,Object> review(@PathVariable @Positive long id,@Valid @RequestBody ReviewMaintenanceRequest r){return service.review(id,r);}
 @PatchMapping("/requests/{id}/status")
 public Map<String,Object> status(@PathVariable @Positive long id,@Valid @RequestBody UpdateMaintenanceStatusRequest r){return service.updateStatus(id,r);}
 @GetMapping("/overdue")
 public List<Map<String,Object>> overdue(){return service.overdue();}
}
