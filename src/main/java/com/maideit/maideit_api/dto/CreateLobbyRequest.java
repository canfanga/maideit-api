package com.maideit.maideit_api.dto;

public class CreateLobbyRequest {
    private String name;
    private Boolean openAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpenAll() {
        return openAll;
    }

    public void setOpenAll(Boolean openAll) {
        this.openAll = openAll;
    }
}
