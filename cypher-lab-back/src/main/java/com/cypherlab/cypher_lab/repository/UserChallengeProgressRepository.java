package com.cypherlab.cypher_lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cypherlab.cypher_lab.models.UserChallengeProgress;

public interface UserChallengeProgressRepository extends JpaRepository<UserChallengeProgress, Long> {
    
    Optional<UserChallengeProgress> findByUsuarioIdAndChallengeId(Long usuarioId, Long challengeId);

    List<UserChallengeProgress> findByUsuarioId(Long usuarioId);

    List<UserChallengeProgress> findByChallengeId(Long challengeId);

    List<UserChallengeProgress> findByUsuarioIdAndSolved(Long usuarioId, boolean solved);

    Long countByUsuarioIdAndSolved(Long usuarioId, boolean solved);

    List<UserChallengeProgress> findByUsuarioIdAndSolvedOrderBySolvedAtDesc(Long usuarioId, boolean solved);
}
