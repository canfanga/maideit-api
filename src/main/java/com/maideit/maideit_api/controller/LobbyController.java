package com.maideit.maideit_api.controller;

import com.maideit.maideit_api.dto.CreateLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyResponse;
import com.maideit.maideit_api.model.Lobby;
import com.maideit.maideit_api.service.LobbyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lobby")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("/create")
    public ResponseEntity<Lobby> createLobby(@RequestBody CreateLobbyRequest request) {
        Lobby lobby = lobbyService.createLobby(request);
        return ResponseEntity.ok(lobby);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinLobbyResponse> joinLobby(@RequestBody JoinLobbyRequest request) {
        JoinLobbyResponse response = lobbyService.joinLobby(request);
        return ResponseEntity.ok(response);
    }
}
