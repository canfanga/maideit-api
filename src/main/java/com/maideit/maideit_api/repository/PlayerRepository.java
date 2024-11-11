package com.maideit.maideit_api.repository;

import com.maideit.maideit_api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
