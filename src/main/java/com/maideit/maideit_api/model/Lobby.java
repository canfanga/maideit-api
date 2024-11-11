package com.maideit.maideit_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Boolean started = false;

    @ElementCollection
    private List<String> members = new ArrayList<>();

    // Setter for 'code'
    public void setCode(String code) {
        this.code = code;
    }

    // Getter for 'members'
    public List<String> getMembers() {
        return members;
    }

    // Setter for 'members' (optional)
    public void setMembers(List<String> members) {
        this.members = members;
    }
}
