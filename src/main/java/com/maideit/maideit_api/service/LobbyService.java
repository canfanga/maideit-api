package com.maideit.maideit_api.service;

import com.maideit.maideit_api.dto.CreateLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyResponse;
import com.maideit.maideit_api.exception.LobbyNotFoundException;
import com.maideit.maideit_api.model.Lobby;
import com.maideit.maideit_api.repository.LobbyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;

    public LobbyService(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    public Lobby createLobby(CreateLobbyRequest request) {
        Lobby lobby = new Lobby();
        lobby.setCode(request.getCode());
        return lobbyRepository.save(lobby);
    }

    public JoinLobbyResponse joinLobby(JoinLobbyRequest request) {
        Optional<Lobby> lobbyOptional = lobbyRepository.findByCode(request.getCode());

        if (lobbyOptional.isEmpty()) {
            throw new LobbyNotFoundException("Lobby with code " + request.getCode() + " not found.");
        }

        Lobby lobby = lobbyOptional.get();
        lobby.getMembers().add(request.getName());
        lobbyRepository.save(lobby);

        return new JoinLobbyResponse("Joined lobby successfully.", true);
    }
}
