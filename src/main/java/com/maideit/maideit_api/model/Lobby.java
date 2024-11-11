package com.maideit.maideit_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Boolean currentPlaying = false;

    @ElementCollection
    private List<String> members = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastGameDate;

    // Getters and Setters
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Boolean getCurrentPlaying() {
        return currentPlaying;
    }

    public void setCurrentPlaying(Boolean currentPlaying) {
        this.currentPlaying = currentPlaying;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastGameDate() {
        return lastGameDate;
    }

    public void setLastGameDate(Date lastGameDate) {
        this.lastGameDate = lastGameDate;
    }
}
