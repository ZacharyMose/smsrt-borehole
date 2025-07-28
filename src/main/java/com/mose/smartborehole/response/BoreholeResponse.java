package com.mose.smartborehole.response;

import com.mose.smartborehole.dto.UserDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BoreholeResponse {
    private UUID id;
    private String location;
    private String name;
    private UserDTO admin;
    private List<UserDTO> technicians;
}
