package com.mose.smartborehole.response;

import com.mose.smartborehole.dto.TeamMemberDTO;

import java.util.List;

public class BoreholeTeamResponse {
    private List<TeamMemberDTO> members;

    public BoreholeTeamResponse(List<TeamMemberDTO> members) {
        this.members = members;
    }

    public List<TeamMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMemberDTO> members) {
        this.members = members;
    }

}
