package com.cypherlab.cypher_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cypherlab.cypher_lab.models.ChallengeModule;

@Repository
public interface ChallengeModuleRepository extends JpaRepository<ChallengeModule, Long>{
    
}