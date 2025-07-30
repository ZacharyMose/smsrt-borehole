package com.mose.smartborehole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoreholeData {
    private String location;
    private String name;
    private UUID adminId;
    private List<UUID> technicianIds;
}
