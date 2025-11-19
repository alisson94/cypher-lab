package com.cypherlab.cypher_lab.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.dto.ModuleProgressDTO;
import com.cypherlab.cypher_lab.dto.UserProgressDTO;
import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.models.UserChallengeProgress;
import com.cypherlab.cypher_lab.repository.ChallengeModuleRepository;
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
    private ChallengeModuleRepository moduleRepository;
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


    //progresso para modulos

    //falta pra todos os modulos

    public List<ModuleProgressDTO> getAllModulesProgress(Long usuarioId) {
        List<ChallengeModule> modules = moduleRepository.findAll();
        
        return modules.stream()
            .map(m -> getModuleProgress(usuarioId, m.getId()))
            .collect(Collectors.toList());
    }
    
    public ModuleProgressDTO getModuleProgress(Long usuarioId, Long moduleId) {
        ChallengeModule module = moduleRepository.findById(moduleId)
            .orElseThrow(() -> new RuntimeException("Módulo não encontrado"));
        
        List<Challenge> moduleChallenges = module.getListChallenges();
        List<UserChallengeProgress> userProgress = progressRepository.findByUsuarioId(usuarioId);

        int totalChallenges = moduleChallenges.size();
        int totalPoints = moduleChallenges.stream()
            .mapToInt(Challenge::getReward)
            .sum();

        List<UserChallengeProgress> solvedInModule = userProgress.stream()
            .filter(p -> p.getSolved())
            .filter(p -> p.getChallenge().getCategory().getId() == moduleId)
            .collect(Collectors.toList());

        int solvedChallenges = solvedInModule.size();
        int earnedPoints = solvedInModule.stream()
            .mapToInt(p -> p.getPointsEarned() != null ? p.getPointsEarned() : 0)
            .sum();

        return new ModuleProgressDTO(
            module.getId(),
            module.getTitle(),
            totalChallenges,
            solvedChallenges,
            totalPoints,
            earnedPoints
        );
    }

    public boolean isModuleCompleted(Long usuarioId, Long moduleId) {
        ChallengeModule module = moduleRepository.findById(moduleId)
            .orElseThrow(() -> new RuntimeException("Módulo não encontrado"));
        
        int totalChallenges = module.getListChallenges().size();
        
        long solvedInModule = progressRepository
            .findByUsuarioIdAndSolved(usuarioId, true)
            .stream()
            .filter(p -> p.getChallenge().getCategory().getId() == moduleId)
            .count();
        
        return solvedInModule == totalChallenges && totalChallenges > 0;
    }
}
