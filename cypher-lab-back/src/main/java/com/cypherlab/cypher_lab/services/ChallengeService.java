package com.cypherlab.cypher_lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cypherlab.cypher_lab.dto.SubmissionResponse;
import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;


@Service
public class ChallengeService {
    
    @Autowired
    private ChallengeRepository challengeRepository;
    
    public ChallengeService() {
    }
    
    public SubmissionResponse validateResponse(long challengeId, String userAnswer) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);
        
        if (challenge == null) {
            return new SubmissionResponse(false, "Desafio não encontrado!");
        }
        
        if (userAnswer == null || userAnswer.trim().isEmpty()) {
            return new SubmissionResponse(false, "Resposta não pode estar vazia!");
        }
        
        String normalizedAnswer = userAnswer.trim().toUpperCase();

        
        if (normalizedAnswer.equals(challenge.getSolutionHash())) {
            return new SubmissionResponse(true, "Parabéns! Resposta correta!");
        } else {
            return new SubmissionResponse(false, "Resposta incorreta. Tente novamente!");
        }
    }

    public java.util.List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
    
    public Challenge getChallengeById(long challengeId) {
        return challengeRepository.findById(challengeId).orElse(null);
    }
}
