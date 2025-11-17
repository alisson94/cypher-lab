package com.cypherlab.cypher_lab.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.dto.ChallengeSubmission;
import com.cypherlab.cypher_lab.dto.ChallengeDTO;
import com.cypherlab.cypher_lab.dto.ChallengeDetails;
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
        String submissionHash = hashSubmission(normalizedAnswer);

        if (submissionHash.equals(challenge.getSolutionHash())) {
            return new SubmissionResponse(true, "Parabéns! Resposta correta!");
        } else {
            return new SubmissionResponse(false, "Resposta incorreta. Tente novamente!");
        }
    }

        public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
    
    public Challenge getChallengeById(long challengeId) {
        return challengeRepository.findById(challengeId).orElse(null);
    }


    public String hashSubmission(String input) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for(byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}

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
            this.hashSubmission(dto.getSolutionHash().trim().toUpperCase()),
            dto.getReward()
        );
        
        return challengeRepository.save(challenge);
    }

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

    
     public List<ChallengeDetails> mapToChallengeDetails(List<Challenge> challenges) {
        return challenges.stream()
            .map(challenge -> new ChallengeDetails(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getCategory() != null ? challenge.getCategory().getTitle() : null,
                challenge.getReward()
            ))
            .toList();
    }
}
