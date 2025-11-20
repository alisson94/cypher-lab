package com.cypherlab.cypher_lab.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypherlab.cypher_lab.dto.ModuleProgressDTO;
import com.cypherlab.cypher_lab.dto.UserProgressDTO;
import com.cypherlab.cypher_lab.models.UserChallengeProgress;
import com.cypherlab.cypher_lab.services.ChallengeService;
import com.cypherlab.cypher_lab.services.UserChallengeProgressService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserChallengeProgressController {

    @Autowired
    private UserChallengeProgressService progressService;

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/user/{userId}/challenges/{challengeId}/submit")
    private ResponseEntity<?> submitAnswer(
            @PathVariable Long userId, 
            @PathVariable Long challengeId, 
            @RequestBody Map<String, String> body) {

        String answer = body.get("answer");
                
        try {
            UserChallengeProgress progress = progressService.submitAnswer(userId, challengeId, answer);
            boolean isCorrect = challengeService.validateResponse(challengeId, answer).isCorrect();
            
            return ResponseEntity.ok(Map.of(
                "solved", progress.getSolved(),
                "attempts", progress.getAttempts(),
                "pointsEarned", progress.getPointsEarned()!= null ? progress.getPointsEarned() : 0,
                "message", isCorrect ? "Resposta correta! Desafio resolvido." : "Resposta incorreta. Tente novamente.",
                "isCorrect", isCorrect ? true : false
            ));


        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }
    
    @GetMapping("/user/{userId}/progress")
    public ResponseEntity<List<UserProgressDTO>> getUserProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getUserProgress(userId));
    }

    @GetMapping("/user/{userId}/solved")
    public ResponseEntity<List<UserProgressDTO>> getSolvedChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getSolvedChallenges(userId));
    }

    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<?> getUserStats(@PathVariable Long userId) {
        Long totalSolved = progressService.getTotalSolved(userId);
        return ResponseEntity.ok(Map.of(
            "totalSolved", totalSolved
        ));
    }

    @GetMapping("/user/{userId}/modules/progress")
    public ResponseEntity<List<ModuleProgressDTO>> getAllModulesProgress(@PathVariable Long userId) {
        try {
            List<ModuleProgressDTO> modulesProgress = progressService.getAllModulesProgress(userId);
            return ResponseEntity.ok(modulesProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}/modules/{moduleId}/progress")
    public ResponseEntity<ModuleProgressDTO> getModuleProgress(
            @PathVariable Long userId, 
            @PathVariable Long moduleId) {
        try {
            ModuleProgressDTO progress = progressService.getModuleProgress(userId, moduleId);
            return ResponseEntity.ok(progress);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}/modules/{moduleId}/completed")
    public ResponseEntity<?> isModuleCompleted(
            @PathVariable Long userId, 
            @PathVariable Long moduleId) {
        try {
            boolean completed = progressService.isModuleCompleted(userId, moduleId);
            return ResponseEntity.ok(Map.of(
                "moduleId", moduleId,
                "completed", completed
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}/streak")
    public ResponseEntity<?> getCurrentStreak(@PathVariable Long userId) {
        try {
            int streak = progressService.getCurrentStreak(userId);
            return ResponseEntity.ok(Map.of(
                "currentStreak", streak
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}/rank")
    public ResponseEntity<?> getUserRank(@PathVariable Long userId) {
        try {
            int rank = progressService.getUserRank(userId);
            return ResponseEntity.ok(Map.of(
                "rank", rank
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
}