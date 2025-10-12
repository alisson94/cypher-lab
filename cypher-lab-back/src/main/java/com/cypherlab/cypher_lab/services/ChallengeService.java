package com.cypherlab.cypher_lab.services;

import org.springframework.stereotype.Service;
import com.cypherlab.cypher_lab.dto.SubmissionResponse;
import com.cypherlab.cypher_lab.models.Challenge;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChallengeService {
    
    // Mapa local para simular banco de dados - desafios de teste
    private Map<Long, Challenge> localChallenges = new HashMap<>();
    
    public ChallengeService() {
        initializeTestChallenges();
    }
    
    private void initializeTestChallenges() {
        // Desafio 1: Caesar Cipher simples
        Challenge challenge1 = new Challenge(
            "Cifra de César",
            "Decodifique a seguinte mensagem cifrada com César (deslocamento 3): FDVDU",
            "Fácil",
            "Criptografia Clássica",
            "CESAR"
        );
        challenge1.setId(1L);
        localChallenges.put(1L, challenge1);
    }
    
    public SubmissionResponse validateResponse(long challengeId, String userAnswer) {
        Challenge challenge = localChallenges.get(challengeId);
        
        if (challenge == null) {
            return new SubmissionResponse(false, "Desafio não encontrado!");
        }
        
        if (userAnswer == null || userAnswer.trim().isEmpty()) {
            return new SubmissionResponse(false, "Resposta não pode estar vazia!");
        }
        
        // Normaliza a resposta do usuário (maiúscula e remove espaços)
        String normalizedAnswer = userAnswer.trim().toUpperCase();

        
        if (normalizedAnswer.equals(challenge.getSolutionHash())) {
            return new SubmissionResponse(true, "Parabéns! Resposta correta!");
        } else {
            return new SubmissionResponse(false, "Resposta incorreta. Tente novamente!");
        }
    }
    
    public Challenge getChallengeById(long challengeId) {
        return localChallenges.get(challengeId);
    }
}
