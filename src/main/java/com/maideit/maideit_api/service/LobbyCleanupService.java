package com.maideit.maideit_api.service;

import com.maideit.maideit_api.repository.LobbyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LobbyCleanupService {

    private final LobbyRepository lobbyRepository;

    public LobbyCleanupService(LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    @Scheduled(fixedRate = 300000) // Run every 5 minutes
    @Transactional
    public void cleanupInactiveLobbies() {
        Date now = new Date();

        lobbyRepository.findAll().forEach(lobby -> {
            long timeSinceCreation = now.getTime() - lobby.getCreatedDate().getTime();

            // Handle null lastGameDate by treating it as the creation date or skipping the check
            Date lastGameDate = lobby.getLastGameDate() != null ? lobby.getLastGameDate() : lobby.getCreatedDate();
            long timeSinceLastGame = now.getTime() - lastGameDate.getTime();

            boolean isEmptyForTooLong = lobby.getMembers().isEmpty() &&
                    TimeUnit.MILLISECONDS.toMinutes(timeSinceCreation) > 30;

            boolean isInactiveForTooLong = !lobby.getCurrentPlaying() &&
                    TimeUnit.MILLISECONDS.toMinutes(timeSinceLastGame) > 60;

            if (isEmptyForTooLong || isInactiveForTooLong) {
                lobbyRepository.delete(lobby);
                System.out.println("Deleted lobby with code: " + lobby.getCode());
            }
        });
    }
}