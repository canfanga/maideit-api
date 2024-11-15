package com.maideit.maideit_api.dto;

public class JoinLobbyResponse {
    private String message;
    private boolean success;
    private LobbyDTO lobby; // Add this field to include lobby information

    // Constructor with parameters
    public JoinLobbyResponse(String message, boolean success, LobbyDTO lobby) {
        this.message = message;
        this.success = success;
        this.lobby = lobby;
    }

    // Default no-args constructor
    public JoinLobbyResponse() {
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public LobbyDTO getLobby() {
        return lobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }
}
