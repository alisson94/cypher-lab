package com.cypherlab.cypher_lab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.repository.ChallengeModuleRepository;

@Service
public class ChallengeModuleService {
    
    @Autowired
    private ChallengeModuleRepository challengeModuleRepository;

    public ChallengeModuleService(){}

    public List<ChallengeModule> getAllChallengeModules() {
        return challengeModuleRepository.findAll();
    }

    public ChallengeModule getChallengeModuleById(long moduleId){
        return challengeModuleRepository.findById(moduleId).orElse(null);
    }
}
