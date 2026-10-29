package com.defense.maintainace.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
public record ReviewMaintenanceRequest(@NotNull Long reviewerId, @NotBlank @Pattern(regexp="(?i)APPROVED|REJECTED") String decision, String note) {}
