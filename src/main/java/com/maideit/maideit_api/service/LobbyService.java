package com.maideit.maideit_api.service;

import com.maideit.maideit_api.dto.CreateLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyRequest;
import com.maideit.maideit_api.dto.JoinLobbyResponse;
import com.maideit.maideit_api.exception.LobbyNotFoundException;
import com.maideit.maideit_api.model.Lobby;
import com.maideit.maideit_api.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
public class LobbyService {

    private final LobbyRepository lobbyRepository;
    private static final int CODE_LENGTH = 5;
    private static final String CODE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    public LobbyService(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    public Lobby createLobby() {
        String uniqueCode = generateUniqueCode();

        Lobby lobby = new Lobby();

        lobby.setCode(uniqueCode);
        lobby.setCreatedDate(new Date());

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
