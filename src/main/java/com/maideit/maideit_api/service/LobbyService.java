package com.maideit.maideit_api.service;

import com.maideit.maideit_api.dto.CreateLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyResponse;
import com.maideit.maideit_api.dto.LobbyDTO;
import com.maideit.maideit_api.exception.LobbyNotFoundException;
import com.maideit.maideit_api.model.Lobby;
import com.maideit.maideit_api.model.Player;
import com.maideit.maideit_api.repository.LobbyRepository;
import com.maideit.maideit_api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private static final int CODE_LENGTH = 5;
    private static final String CODE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private final PlayerRepository playerRepository;

    @Autowired
    public LobbyService(LobbyRepository lobbyRepository, PlayerRepository playerRepository) {
        this.lobbyRepository = lobbyRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional // Ensure transactional behavior
    public Lobby createLobby(CreateLobbyRequest request) {
        // Create a new player using the provided name
        Player player = new Player();
        player.setName(request.getName());

        // Generate a unique lobby code
        String uniqueCode = generateUniqueCode();

        // Create a new lobby and set its properties
        Lobby lobby = new Lobby();
        lobby.setCode(uniqueCode);
        lobby.setCreatedDate(new Date());

        // Associate the player with the lobby
        player.setLobby(lobby);
        lobby.getMembers().add(player);

        return lobbyRepository.save(lobby);
    }


    private String generateUniqueCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (lobbyRepository.findByCode(code).isPresent());
        return code;
    }

    private String generateRandomCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CODE_CHARACTERS.charAt(RANDOM.nextInt(CODE_CHARACTERS.length())));
        }
        return code.toString();
    }

    public JoinLobbyResponse joinLobby(JoinLobbyRequest request) {
        // Validate inputs
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        if (request.getCode() == null || request.getCode().isBlank()) {
            throw new IllegalArgumentException("Lobby code cannot be null or empty.");
        }

        // Find the lobby
        Optional<Lobby> lobbyOptional = lobbyRepository.findByCode(request.getCode());
        if (lobbyOptional.isEmpty()) {
            throw new LobbyNotFoundException("Lobby with code " + request.getCode() + " not found.");
        }

        Lobby lobby = lobbyOptional.get();

        // Create and add player to the lobby
        Player player = new Player();
        player.setName(request.getName());
        player.setLobby(lobby);
        lobby.getMembers().add(player);


        lobbyRepository.save(lobby);

        // Convert Lobby to LobbyDTO
        LobbyDTO lobbyDTO = new LobbyDTO(
                lobby.getCode(),
                lobby.getMembers()
        );

        return new JoinLobbyResponse("Joined lobby successfully.", true, lobbyDTO);
    }
}
