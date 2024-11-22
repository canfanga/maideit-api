package com.maideit.maideit_api.controller;

import com.maideit.maideit_api.dto.*;
import com.maideit.maideit_api.model.Lobby;
import com.maideit.maideit_api.service.LobbyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lobbies")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Lobby> createLobby(@RequestBody CreateLobbyRequest request
                                             ) {
        Lobby lobby = lobbyService.createLobby(request);
        return ResponseEntity.ok(lobby);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinLobbyResponse> joinLobby(@RequestBody JoinLobbyRequest request) {
        JoinLobbyResponse response = lobbyService.joinLobby(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public")
    public ResponseEntity<PaginatedPublicLobbiesResponse> getPublicLobbies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginatedPublicLobbiesResponse response = lobbyService.getAllPublicLobbies(page, size);
        return ResponseEntity.ok(response);
    }


}
