package com.cypherlab.cypher_lab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.dto.ChallengeDetails;
import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.services.ChallengeModuleService;
import com.cypherlab.cypher_lab.services.ChallengeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Permite requisições do frontend 
public class ChallengeModuleController {

    @Autowired
    private ChallengeModuleService challengeModuleService;

    @Autowired
    private ChallengeService challengeService;

    @GetMapping("/modules")
    public ResponseEntity<java.util.List<ChallengeModule>> getAllModules() {
        java.util.List<ChallengeModule> modules = challengeModuleService.getAllChallengeModules();
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<ChallengeModule> getModuleById(@PathVariable long moduleId) {
        ChallengeModule module = challengeModuleService.getChallengeModuleById(moduleId);
        if (module != null) {
            return ResponseEntity.ok(module);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/module/{moduleId}/challenges")
    public ResponseEntity<List<ChallengeDetails>> getChallengesFromModule(@PathVariable long moduleId) {
        List<Challenge> challenges = challengeModuleService.getChallengesFromModule(moduleId);
        if (challenges != null) {
            List<ChallengeDetails> dtos = challengeService.mapToChallengeDetails(challenges);
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //ADMINISTRATIVOS
    @PostMapping("/admin/module")
    public ResponseEntity<ChallengeModule> createModule(@RequestBody ChallengeModule module) {
        ChallengeModule createdModule = challengeModuleService.createChallengeModule(module);
        return ResponseEntity.ok(createdModule);
    }
    
    @PutMapping("/admin/module/{moduleId}")
    public ResponseEntity<ChallengeModule> updateModule(@PathVariable long moduleId, @RequestBody ChallengeModule moduleDetails) {
        ChallengeModule updatedModule = challengeModuleService.updateChallengeModule(moduleId, moduleDetails);
        if (updatedModule != null) {
            return ResponseEntity.ok(updatedModule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/module/{moduleId}")
    public ResponseEntity<Void> deleteModule(@PathVariable long moduleId) {
        challengeModuleService.deleteChallengeModule(moduleId);
        return ResponseEntity.noContent().build();
    }

    //  private List<ChallengeDetails> mapToChallengeDetails(List<Challenge> challenges) {
    //     return challenges.stream()
    //         .map(challenge -> new ChallengeDetails(
    //             challenge.getId(),
    //             challenge.getTitle(),
    //             challenge.getDescription(),
    //             challenge.getDifficulty(),
    //             challenge.getCategory() != null ? challenge.getCategory().getTitle() : null,
    //             challenge.getReward()
    //         ))
    //         .toList();
    // }


}