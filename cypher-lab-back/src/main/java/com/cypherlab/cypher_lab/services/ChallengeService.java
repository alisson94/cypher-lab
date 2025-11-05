package com.cypherlab.cypher_lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cypherlab.cypher_lab.dto.ChallengeDTO;
import com.cypherlab.cypher_lab.dto.SubmissionResponse;
import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;
import com.cypherlab.cypher_lab.repository.ChallengeModuleRepository;


@Service
public class ChallengeService {
    
    @Autowired
    private ChallengeRepository challengeRepository;
    
    @Autowired
    private ChallengeModuleRepository challengeModuleRepository;
    
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

    // public Challenge createChallenge(Challenge challenge) {
    //     return challengeRepository.save(challenge);
    // }

    public Challenge createChallengeFromDTO(ChallengeDTO dto) {
        //procura modulo
        ChallengeModule module = challengeModuleRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Módulo não encontrado com ID: " + dto.getCategoryId()));
        
        //cria desafio
        Challenge challenge = new Challenge(
            dto.getTitle(),
            dto.getDescription(),
            dto.getDifficulty(),
            module,
            dto.getSolutionHash(),
            dto.getReward()
        );
        
        return challengeRepository.save(challenge);
    }

    // public Challenge updateChallenge(long challengeId, Challenge challengeDetails) {
    //     return challengeRepository.findById(challengeId).map(challenge -> {
    //         challenge.setTitle(challengeDetails.getTitle());
    //         challenge.setDescription(challengeDetails.getDescription());
    //         challenge.setSolutionHash(challengeDetails.getSolutionHash());
    //         challenge.setCategory(challengeDetails.getCategory());
    //         return challengeRepository.save(challenge);
    //     }).orElse(null);
    // }

    public Challenge updateChallengeFromDTO(long challengeId, ChallengeDTO dto) {
        return challengeRepository.findById(challengeId).map(challenge -> {
            challenge.setTitle(dto.getTitle());
            challenge.setDescription(dto.getDescription());
            challenge.setDifficulty(dto.getDifficulty());
            challenge.setSolutionHash(dto.getSolutionHash());
            challenge.setReward(dto.getReward());
            
            if (dto.getCategoryId() != null) {
                ChallengeModule module = challengeModuleRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Módulo não encontrado com ID: " + dto.getCategoryId()));
                challenge.setCategory(module);
            }
            
            return challengeRepository.save(challenge);
        }).orElse(null);
    }

    public void deleteChallenge(long challengeId) {
        challengeRepository.deleteById(challengeId);
    }
}
