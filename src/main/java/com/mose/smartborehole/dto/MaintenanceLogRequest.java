package com.mose.smartborehole.dto;

import java.util.UUID;

public record MaintenanceLogRequest(
        String description,
        String status,
        UUID boreholeId,
        UUID userId
) {
}
