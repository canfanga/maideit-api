package com.maideit.maideit_api.dto;

import java.util.List;

public class PaginatedPublicLobbiesResponse {
    private List<LobbyDTO> lobbies;
    private int currentPage;
    private int totalPages;
    private long totalElements;

    // Constructor, getters, and setters
    public PaginatedPublicLobbiesResponse(List<LobbyDTO> lobbies, int currentPage, int totalPages, long totalElements) {
        this.lobbies = lobbies;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<LobbyDTO> getLobbies() {
        return lobbies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
