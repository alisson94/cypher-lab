package com.cypherlab.cypher_lab.services;

import java.util.List;

import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.models.Usuario;
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

    public List<UserChallengeProgress> getUserProgress(Long usuarioId) {
        return progressRepository.findByUsuarioId(usuarioId);
    }

    public List<UserChallengeProgress> getSolvedChallenges(Long usuarioId) {
        return progressRepository.findByUsuarioIdAndSolved(usuarioId, true);
    }

    public Long getTotalSolved(Long usuarioId) {
        return progressRepository.countByUsuarioIdAndSolved(usuarioId, true);
    }

}
