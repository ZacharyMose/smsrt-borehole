package com.mose.smartborehole.dto;

public class TeamMemberDTO {
    private String name;
    private String email;

    public TeamMemberDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
