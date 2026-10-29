package com.defense.maintainace.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
public record UpdateMaintenanceStatusRequest(
 @NotNull Long changedBy,
 @NotBlank @Pattern(regexp="(?i)SCHEDULED|IN_PROGRESS|COMPLETED|CANCELLED") String status,
 LocalDate scheduledDate, LocalDate completedDate, String note
) {}
