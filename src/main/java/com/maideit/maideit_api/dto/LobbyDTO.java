package com.maideit.maideit_api.dto;

import com.maideit.maideit_api.model.Player;

import java.util.List;

public class LobbyDTO {
    private String code;
    private List<Player> members; // List of member names

    public LobbyDTO(String code, List<Player> members) {
        this.code = code;
        this.members = members;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Player> getMembers() {
        return members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }
}
