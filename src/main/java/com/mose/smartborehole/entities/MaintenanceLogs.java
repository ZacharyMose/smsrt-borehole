package com.mose.smartborehole.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaintenanceLogs {
    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    private String status; // e.g. completed, pending, scheduled

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "borehole_id")
    private Boreholes borehole;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users performedBy;
}
