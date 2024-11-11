package com.maideit.maideit_api.repository;

import com.maideit.maideit_api.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Optional<Lobby> findByCode(String code);
}
