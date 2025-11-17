package com.cypherlab.cypher_lab.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.dto.UserProgressDTO;
import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.UserChallengeProgress;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;
import com.cypherlab.cypher_lab.repository.UserChallengeProgressRepository;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;

@Service
public class UserChallengeProgressService {
    
    @Autowired
    private UserChallengeProgressRepository progressRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeService challengeService;

    public UserChallengeProgress submitAnswer(Long userId, Long challengeId, String answer) {
        
        //buscar usuario e desafio
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Challenge challenge = challengeRepository.findById(challengeId)
            .orElseThrow(() -> new RuntimeException("Desafio não encontrado"));

        //buscar progresso do usuario no desafio
        UserChallengeProgress progress = progressRepository
            .findByUsuarioIdAndChallengeId(userId, challengeId)
            .orElse(new UserChallengeProgress(usuario, challenge));

        //incrementar tentativas
        progress.setAttempts(progress.getAttempts() + 1);
        progress.setLastAttemptAt(java.time.LocalDateTime.now());

        //ja foi resolvido antes?
        if (progress.getSolved()) {
            return progressRepository.save(progress); //ja resolvido, nao faz nada
        }

        //verificar resposta
        String hasedAnswer = challengeService.hashSubmission(answer.trim().toUpperCase());
        if(hasedAnswer.equals(challenge.getSolutionHash())) {
            //resposta correta
            progress.setSolved(true);
            progress.setSolvedAt(java.time.LocalDateTime.now());
            progress.setPointsEarned(challenge.getReward());

            usuario.setPontos(usuario.getPontos() + challenge.getReward());
            usuarioRepository.save(usuario);
        }

        return progressRepository.save(progress);
    }

    public List<UserProgressDTO> getUserProgress(Long usuarioId) {
        List<UserChallengeProgress> progress = progressRepository.findByUsuarioId(usuarioId);
        
        return progress.stream()
            .map(p -> new UserProgressDTO(
                p.getId(),
                p.getChallenge().getId(),
                p.getChallenge().getTitle(),
                p.getChallenge().getDifficulty(),
                p.getSolved(),
                p.getAttempts(),
                p.getPointsEarned(),
                p.getSolvedAt(),
                p.getLastAttemptAt()
            ))
            .collect(Collectors.toList());
    }

    public List<UserProgressDTO> getSolvedChallenges(Long usuarioId) {
        List<UserChallengeProgress> progress = progressRepository.findByUsuarioIdAndSolved(usuarioId, true);
        
        return progress.stream()
            .map(p -> new UserProgressDTO(
                p.getId(),
                p.getChallenge().getId(),
                p.getChallenge().getTitle(),
                p.getChallenge().getDifficulty(),
                p.getSolved(),
                p.getAttempts(),
                p.getPointsEarned(),
                p.getSolvedAt(),
                p.getLastAttemptAt()
            ))
            .collect(Collectors.toList());
    }

    public Long getTotalSolved(Long usuarioId) {
        return progressRepository.countByUsuarioIdAndSolved(usuarioId, true);
    }

}
