package com.maideit.maideit_api.repository;

import com.maideit.maideit_api.model.Lobby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, Long> {
    Optional<Lobby> findByCode(String code);
    Page<Lobby> findByOpenAll(boolean open, Pageable pageable);
}
