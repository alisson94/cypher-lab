package com.cypherlab.cypher_lab.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.cypherlab.cypher_lab.dto.ChallengeDTO;
import com.cypherlab.cypher_lab.dto.ChallengeSubmission;
import com.cypherlab.cypher_lab.dto.ChallengeDetails;
import com.cypherlab.cypher_lab.dto.SubmissionResponse;
import com.cypherlab.cypher_lab.services.ChallengeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cypherlab.cypher_lab.models.Challenge;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChallengeController {
    
    @Autowired
    private ChallengeService challengeService;
    
    @PostMapping("/challenges/{challengeId}/submit")
    public ResponseEntity<SubmissionResponse> submitChallenge(
        @PathVariable long challengeId,
        @RequestBody ChallengeSubmission submission
    ) {
        SubmissionResponse response = challengeService.validateResponse(challengeId, submission.getAns());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/challenges/{challengeId}")
    public ResponseEntity<ChallengeDetails> getChallenge(@PathVariable long challengeId) {
        Challenge challenge = challengeService.getChallengeById(challengeId);
        if (challenge != null) {
            ChallengeDetails challengeDetails = new ChallengeDetails(
                challenge.getId(), 
                challenge.getTitle(), 
                challenge.getDescription(), 
                challenge.getDifficulty(), 
                challenge.getCategory().getTitle(), 
                challenge.getReward()
            );
            return ResponseEntity.ok(challengeDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeDetails>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        List<ChallengeDetails> dtos = challengeService.mapToChallengeDetails(challenges);
        return ResponseEntity.ok(dtos);
    }

    //administrativos
    @PostMapping("/admin/challenge")
    public ResponseEntity<Challenge> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        try {
            Challenge createdChallenge = challengeService.createChallengeFromDTO(challengeDTO);
            return ResponseEntity.status(201).body(createdChallenge);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/admin/challenge/{challengeId}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable long challengeId, @RequestBody ChallengeDTO challengeDTO){
        try {
            Challenge updateChallenge = challengeService.updateChallengeFromDTO(challengeId, challengeDTO);
            if(updateChallenge != null){
                return ResponseEntity.ok(updateChallenge);
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/admin/challenge/{challengeId}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable long challengeId){
        challengeService.deleteChallenge(challengeId);
        return ResponseEntity.noContent().build();
    }
}
