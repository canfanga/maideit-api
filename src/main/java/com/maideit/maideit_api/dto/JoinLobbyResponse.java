package com.maideit.maideit_api.dto;

public class JoinLobbyResponse {
    private String message;
    private boolean success;

    // Constructor with parameters
    public JoinLobbyResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Default no-args constructor (optional)
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
}
