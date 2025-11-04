package com.cypherlab.cypher_lab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.services.ChallengeModuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Permite requisições do frontend 
public class ChallengeModuleController {

    @Autowired
    private ChallengeModuleService challengeModuleService;

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

    @GetMapping("/modules/{moduleId}/challenges")
    public ResponseEntity<java.util.List<Challenge>> getChallengesFromModule(@PathVariable long moduleId) {
        java.util.List<Challenge> challenges = challengeModuleService.getChallengesFromModule(moduleId);
        if (challenges != null) {
            return ResponseEntity.ok(challenges);
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
    

}