package com.mose.smartborehole.response;

import com.mose.smartborehole.dto.TechnicianDTO;
import com.mose.smartborehole.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoreholeResponse {
    private UUID id;
    private String location;
    private String name;
    private List<TechnicianDTO> technicians;
}
