package com.mose.smartborehole.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceLogsResponse {
    private UUID id;
    private String description;
    private String technicianName;
    private LocalDateTime maintenanceDate;
    private String boreholeName;
}
