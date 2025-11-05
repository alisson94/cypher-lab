package com.cypherlab.cypher_lab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cypherlab.cypher_lab.dto.ChallengeSubmission;
import com.cypherlab.cypher_lab.dto.SubmissionResponse;
import com.cypherlab.cypher_lab.services.ChallengeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Challenge> getChallenge(@PathVariable long challengeId) {
        Challenge challenge = challengeService.getChallengeById(challengeId);
        if (challenge != null) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }
}
