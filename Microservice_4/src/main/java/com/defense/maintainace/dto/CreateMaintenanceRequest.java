package com.defense.maintainace.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
public record CreateMaintenanceRequest(
 @NotNull Long requesterId,
 @NotBlank @Pattern(regexp="(?i)VEHICLE|EQUIPMENT") String resourceType,
 @NotNull Long resourceId,
 @NotBlank @Pattern(regexp="(?i)PREVENTIVE|CORRECTIVE") String maintenanceType,
 @NotBlank String description,
 @NotBlank @Pattern(regexp="(?i)LOW|NORMAL|HIGH|URGENT") String priority
) {}
