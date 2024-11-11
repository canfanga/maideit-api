package com.maideit.maideit_api.dto;

public class JoinLobbyRequest {
    private String name;
    private String code;

    // Getter for 'code'
    public String getCode() {
        return code;
    }

    // Setter for 'code'
    public void setCode(String code) {
        this.code = code;
    }

    // Getter for 'name'
    public String getName() {
        return name;
    }

    // Setter for 'name'
    public void setName(String name) {
        this.name = name;
    }
}
