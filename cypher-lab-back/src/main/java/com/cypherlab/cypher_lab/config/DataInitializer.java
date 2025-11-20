package com.cypherlab.cypher_lab.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cypherlab.cypher_lab.models.Challenge;
import com.cypherlab.cypher_lab.models.ChallengeModule;
import com.cypherlab.cypher_lab.models.Usuario;
import com.cypherlab.cypher_lab.repository.ChallengeRepository;
import com.cypherlab.cypher_lab.services.ChallengeService;
import com.cypherlab.cypher_lab.repository.ChallengeModuleRepository;
import com.cypherlab.cypher_lab.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ChallengeRepository challengeRepository;
    private final ChallengeModuleRepository challengeModuleRepository;
    private final ChallengeService challengeService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ChallengeRepository challengeRepository, 
                          ChallengeModuleRepository challengeModuleRepository,
                          ChallengeService challengeService,
                          UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.challengeRepository = challengeRepository;
        this.challengeModuleRepository = challengeModuleRepository;
        this.challengeService = challengeService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // Criar usuário admin se não existir
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            admin.setPontos(0);
            usuarioRepository.save(admin);
        }
        
        if(challengeRepository.count() > 0) {
            return;
        }

        // 1. Criar e salvar os módulos primeiro
        ChallengeModule moduloCriptografiaClassica = new ChallengeModule("Criptografia Clássica");
        moduloCriptografiaClassica = challengeModuleRepository.save(moduloCriptografiaClassica);

        // 2. Criar os desafios associando ao módulo salvo
        Challenge desafio1 = new Challenge(
            "Cifra de César",
            "Descriptografe a seguinte mensagem cifrada com César (deslocamento 3): 'SBWKRQ'.<br><br>Instruções: Resolva manualmente. Analise a cifra de César, identifique o deslocamento e recupere a mensagem original letra por letra.",
            "Fácil",
            moduloCriptografiaClassica,  // Passa o módulo salvo
            challengeService.hashSubmission("PYTHON"),
            10
        );

        Challenge desafio2 = new Challenge(
            "Cifra de Vigenère",
            "Descriptografe a mensagem 'MTCQWSMOCMCBTEEET' utilizando a cifra de Vigenère com a chave 'MACACO'.<br><br>Instruções: Resolva de forma computacional ou manual. Aplique o algoritmo da cifra de Vigenère, utilizando a chave fornecida para decifrar cada letra da mensagem.",
            "Médio",
            moduloCriptografiaClassica,  // Passa o módulo salvo
            challengeService.hashSubmission("ATAQUEAOAMANHECER"),
            20
        );

        // 3. Salvar os desafios
        challengeRepository.saveAll(Arrays.asList(desafio1, desafio2));
    }
}
