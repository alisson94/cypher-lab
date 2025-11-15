package com.cypherlab.cypher_lab.controllers;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.models.UserChallengeProgress;
import com.cypherlab.cypher_lab.services.UserChallengeProgressService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserChallengeProgressController {

    @Autowired
    private UserChallengeProgressService progressService;

    @PostMapping("/user/{userId}/challenges/{challengeId}/submit")
    private ResponseEntity<?> submitAnswer(
            @PathVariable Long userId, 
            @PathVariable Long challengeId, 
            @RequestBody Map<String, String> body) {

        String answer = body.get("answer");
                
        try {
            UserChallengeProgress progress = progressService.submitAnswer(userId, challengeId, answer);
            
            return ResponseEntity.ok(Map.of(
                "solved", progress.getSolved(),
                "attempts", progress.getAttempts(),
                "pointsEarned", progress.getPointsEarned()!= null ? progress.getPointsEarned() : 0,
                "message", progress.getSolved() ? "Resposta correta! Desafio resolvido." : "Resposta incorreta. Tente novamente."
            ));


        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }
    
    @GetMapping("/user/{userId}/progress")
    public ResponseEntity<List<UserChallengeProgress>> getUserProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getUserProgress(userId));
    }

    @GetMapping("/user/{userId}/solved")
    public ResponseEntity<List<UserChallengeProgress>> getSolvedChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getSolvedChallenges(userId));
    }

    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<?> getUserStats(@PathVariable Long userId) {
        Long totalSolved = progressService.getTotalSolved(userId);
        return ResponseEntity.ok(Map.of(
            "totalSolved", totalSolved
        ));
    }
}