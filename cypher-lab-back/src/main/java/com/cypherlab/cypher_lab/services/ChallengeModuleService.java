package com.cypherlab.cypher_lab.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.models.Challenge;
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

    public List<Challenge> getChallengesFromModule(long moduleId){
        ChallengeModule module = getChallengeModuleById(moduleId);
        
        if (module != null){
            return module.getListChallenges();
        }
        return new ArrayList<>();
    }

    public ChallengeModule createChallengeModule(ChallengeModule module) {
        return challengeModuleRepository.save(module);
    }

    public ChallengeModule updateChallengeModule(long moduleId, ChallengeModule moduleDetails) {
        return challengeModuleRepository.findById(moduleId).map(module -> {
            module.setTitle(moduleDetails.getTitle());
            module.setIcon(moduleDetails.getIcon());
            
            return challengeModuleRepository.save(module);
        }).orElse(null);
    }

    public void deleteChallengeModule(long moduleId) {
        challengeModuleRepository.deleteById(moduleId);
    }
}
