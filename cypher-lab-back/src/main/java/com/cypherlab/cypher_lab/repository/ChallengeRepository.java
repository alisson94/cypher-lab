package com.cypherlab.cypher_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cypherlab.cypher_lab.models.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}